package io.github.vincentvibe3.anitraklib

import io.ktor.client.statement.*
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.datetime.Clock
import kotlin.math.ceil
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes

object RateLimiting {

    data object Apis{
        val anilist =  ApiRateLimitConfig(
            90, 90, false, 0, -1, 1.minutes
        )
    }

    private val mutex = Mutex()

    data class ApiRateLimitConfig(
        var rateLimit: Int,
        var remaining: Int,
        var isRateLimited: Boolean,
        var retryAfter: Long,
        var reset: Long,
        val rateLimitTimeWindow:Duration
    )

    private fun parseAnilistHeaders(response: HttpResponse) {
        val config = Apis.anilist
        config.rateLimit = response.headers["X-RateLimit-Limit"]?.toInt() ?: 90
        config.remaining = response.headers["X-RateLimit-Remaining"]?.toInt() ?: 0
//            if (config.remaining == 89){
        config.reset = Clock.System.now().plus(1.minutes).epochSeconds
//            }
        val resetHeader = response.headers["X-RateLimit-Reset"]?.toLong()
        val retryAfterHeader = response.headers["Retry-After"]?.toInt()
        if (resetHeader!=null){
            config.reset = resetHeader
        }
        config.retryAfter = ceil((config.rateLimitTimeWindow.inWholeMilliseconds.toDouble()/config.rateLimit.toDouble())).toLong()
        if (retryAfterHeader!=null){
            config.retryAfter = retryAfterHeader.toLong()
        }
    }

    suspend fun <T> execute(block:suspend () -> Pair<HttpResponse?, T>): T {
        val result = mutex.withLock {
            val next = getNextRequestTime()
            delay(next)
            val response = block()
            response.first?.let { parseAnilistHeaders(it) }
            response.second
        }
        return result
    }

    private fun getNextRequestTime(): Long {
        return Apis.anilist.retryAfter
    }

}
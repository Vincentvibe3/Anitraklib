package io.github.vincentvibe3.anitraklib.anilist.types

import io.github.vincentvibe3.anitraklib.anilist.serialization.AnilistUser
import kotlinx.serialization.Serializable

@Serializable
data class Recommendation(
    val id: Int,
    val rating: Int,
    val userRating: RecommendationRating,
    val media: Media? = null,
    val mediaRecommendation: Media?,
    val user: AnilistUser? = null
) {
    @Serializable
    enum class RecommendationRating {
        NO_RATING, RATE_UP, RATE_DOWN
    }
}

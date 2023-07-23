package io.github.vincentvibe3.anitraklib.anilist.types

import kotlinx.serialization.Serializable

@Serializable
data class StatusDistribution(
    val status: MediaListStatus,
    val amount: Int
)

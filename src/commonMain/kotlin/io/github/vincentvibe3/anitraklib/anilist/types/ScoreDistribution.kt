package io.github.vincentvibe3.anitraklib.anilist.types

import kotlinx.serialization.Serializable

@Serializable
data class ScoreDistribution(
    val score: Int,
    val amount: Int
)

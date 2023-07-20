package io.github.vincentvibe3.anilist.types

import kotlinx.serialization.Serializable

@Serializable
data class ScoreDistribution(
    val score:Int,
    val amount:Int
)

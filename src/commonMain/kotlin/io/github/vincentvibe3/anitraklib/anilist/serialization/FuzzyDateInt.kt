package io.github.vincentvibe3.anitraklib.anilist.serialization

import kotlinx.serialization.Serializable

@Serializable
data class FuzzyDateInt(
    val year: Int?,
    val month: Int?,
    val day: Int?
)
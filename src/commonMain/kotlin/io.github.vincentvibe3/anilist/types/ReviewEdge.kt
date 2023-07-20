package io.github.vincentvibe3.anilist.types

import kotlinx.serialization.Serializable

@Serializable
data class ReviewEdge(
    val node:Review
)

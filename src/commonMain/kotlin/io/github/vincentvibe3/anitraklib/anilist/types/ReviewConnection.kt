package io.github.vincentvibe3.anitraklib.anilist.types

import kotlinx.serialization.Serializable

@Serializable
data class ReviewConnection(
    val edges: List<ReviewEdge>? = null,
    val nodes: List<Review>,
    val pageInfo: PageInfo
)

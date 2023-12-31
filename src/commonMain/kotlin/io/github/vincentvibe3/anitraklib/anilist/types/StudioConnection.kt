package io.github.vincentvibe3.anitraklib.anilist.types

import kotlinx.serialization.Serializable

@Serializable
data class StudioConnection(
    val edges: List<StudioEdge>? = null,
    val nodes: List<Studio>,
    val pageInfo: PageInfo
)

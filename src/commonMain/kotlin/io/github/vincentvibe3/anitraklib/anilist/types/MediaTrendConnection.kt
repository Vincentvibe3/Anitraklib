package io.github.vincentvibe3.anitraklib.anilist.types

import kotlinx.serialization.Serializable

@Serializable
data class MediaTrendConnection(
    val edges: List<MediaTrendEdge>? = null,
    val nodes: List<MediaTrend>,
    val pageInfo: PageInfo
)

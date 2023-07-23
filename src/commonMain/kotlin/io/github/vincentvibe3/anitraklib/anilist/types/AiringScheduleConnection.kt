package io.github.vincentvibe3.anitraklib.anilist.types

import AiringSchedule
import kotlinx.serialization.Serializable

@Serializable
data class AiringScheduleConnection(
    val edges: List<AiringScheduleEdge>? = null,
    val nodes: List<AiringSchedule>,
    val pageInfo: PageInfo
)

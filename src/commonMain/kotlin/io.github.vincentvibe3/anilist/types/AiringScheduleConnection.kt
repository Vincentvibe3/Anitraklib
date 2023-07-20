package io.github.vincentvibe3.anilist.types

import AiringSchedule
import kotlinx.serialization.Serializable

@Serializable
data class AiringScheduleConnection(
    val edges:List<AiringScheduleEdge>?=null,
    val nodes:List<AiringSchedule>,
    val pageInfo: PageInfo
)

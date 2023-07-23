package io.github.vincentvibe3.anitraklib.anilist.types

import AiringSchedule
import kotlinx.serialization.Serializable

@Serializable
data class AiringScheduleEdge(
    val node: AiringSchedule,
    val id: Int
)

package io.github.vincentvibe3.anilist.types

import kotlinx.serialization.Serializable

@Serializable
data class StaffConnection(
    val edges: List<StaffEdge>?=null,
    val nodes:List<Staff>,
    val pageInfo: PageInfo
)

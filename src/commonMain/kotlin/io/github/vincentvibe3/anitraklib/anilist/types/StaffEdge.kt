package io.github.vincentvibe3.anitraklib.anilist.types

import kotlinx.serialization.Serializable

@Serializable
data class StaffEdge(
    val node: Staff,
    val id: Int,
    val role: String,
    val favouriteOrder: Int?=null
)

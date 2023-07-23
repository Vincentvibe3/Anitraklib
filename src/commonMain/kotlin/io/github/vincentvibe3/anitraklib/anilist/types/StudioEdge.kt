package io.github.vincentvibe3.anitraklib.anilist.types

import kotlinx.serialization.Serializable

@Serializable
data class StudioEdge(
    val node: Studio,
    val id: Int,
    val isMain: Boolean,
    val favouriteOrder: Int
)

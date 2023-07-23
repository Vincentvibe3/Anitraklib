package io.github.vincentvibe3.anitraklib.anilist.types

import kotlinx.serialization.Serializable

@Serializable
class Studio(
    val id: Int,
    val name: String? = null,
    val isAnimationStudio: Boolean? = null,
    val media: MediaConnection? = null,
    val siteUrl: String? = null,
    val isFavourite: Boolean? = null,
    val favourites: Int? = null
)
package io.github.vincentvibe3.anilist.types

import kotlinx.serialization.Serializable

@Serializable
data class Favorites(
    val anime:Connection?=null,
    val manga:Connection?=null,
    val characters:Connection?=null,
    val staff:Connection?=null,
    val studios:Connection?=null
)
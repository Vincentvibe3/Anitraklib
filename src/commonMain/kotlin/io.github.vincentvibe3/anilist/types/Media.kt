package io.github.vincentvibe3.anilist.types

import kotlinx.serialization.Serializable

@Serializable
data class Media(
    val id:Int,
    val isFavourite:Boolean
)
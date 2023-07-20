package io.github.vincentvibe3.anilist.types

import kotlinx.serialization.Serializable

@Serializable
data class MediaTag(
    val id:Int,
    val name:String,
    val description:String,
    val category:String,
    val rank:Int,
    val isGeneralSpoiler:Boolean,
    val isMediaSpoiler:Boolean,
    val isAdult:Boolean,
    val userId:Int?=null,
)

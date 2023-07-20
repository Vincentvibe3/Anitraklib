package io.github.vincentvibe3.anilist.types

import kotlinx.serialization.Serializable

@Serializable
data class MediaConnection(
    val edges:List<MediaEdge>?=null,
    val nodes:List<Media>,
    val pageInfo:PageInfo
)
package io.github.vincentvibe3.anitraklib.anilist.serialization

import kotlinx.serialization.Serializable

@Serializable
data class ViewerResult(
    val Viewer: AnilistUser
)
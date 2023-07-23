package io.github.vincentvibe3.anitraklib.anilist.types

import kotlinx.serialization.Serializable

@Serializable
data class MediaTrend(
    val mediaId: Int? = null,
    val date: Int? = null,
    val trending: Int? = null,
    val averageScore: Int?,
    val popularity: Int?,
    val inProgress: Int?,
    val releasing: Boolean? = null,
    val episode: Int?,
    val media: Media? = null
)

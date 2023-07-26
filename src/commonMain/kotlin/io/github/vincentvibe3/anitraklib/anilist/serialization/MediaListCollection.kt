package io.github.vincentvibe3.anitraklib.anilist.serialization

import io.github.vincentvibe3.anitraklib.anilist.types.MediaList
import io.github.vincentvibe3.anitraklib.anilist.types.MediaListStatus
import kotlinx.serialization.Serializable

@Serializable
data class MediaListCollection(
    val lists: List<MediaListGroup>,
    val user: AnilistUser,
    val hasNextChunk: Boolean
) {
    @Serializable
    data class MediaListGroup(
        val entries: List<MediaList>,
        val name: String,
        val isCustomList: Boolean,
        val isSplitCompletedList: Boolean,
        val status: MediaListStatus?
    )
}
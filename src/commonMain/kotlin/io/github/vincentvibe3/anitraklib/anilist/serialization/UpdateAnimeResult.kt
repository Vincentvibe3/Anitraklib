package io.github.vincentvibe3.anitraklib.anilist.serialization

import io.github.vincentvibe3.anitraklib.anilist.types.MediaList
import kotlinx.serialization.Serializable

@Serializable
data class UpdateAnimeResult(
    val SaveMediaListEntry: MediaList?
)
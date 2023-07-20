package io.github.vincentvibe3.anilist.serialization

import io.github.vincentvibe3.anilist.types.MediaList
import kotlinx.serialization.Serializable

@Serializable
data class UpdateAnimeResult(
    val SaveMediaListEntry:MediaList
)
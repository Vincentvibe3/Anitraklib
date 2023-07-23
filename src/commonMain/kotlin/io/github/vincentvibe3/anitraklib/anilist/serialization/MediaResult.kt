package io.github.vincentvibe3.anitraklib.anilist.serialization

import io.github.vincentvibe3.anitraklib.anilist.types.Media
import kotlinx.serialization.Serializable

@Serializable
data class MediaResult(
    val Media: Media?
)
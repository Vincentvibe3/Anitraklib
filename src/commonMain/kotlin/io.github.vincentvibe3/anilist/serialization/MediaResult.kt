package io.github.vincentvibe3.anilist.serialization

import io.github.vincentvibe3.anilist.types.Media
import kotlinx.serialization.Serializable

@Serializable
data class MediaResult(
    val Media:Media?
)
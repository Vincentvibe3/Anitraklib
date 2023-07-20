package io.github.vincentvibe3.anilist.types

import kotlinx.serialization.Serializable

@Serializable
enum class MediaStatus {
    FINISHED, RELEASING, NOT_YET_RELEASED,
    CANCELLED, HIATUS
}
package io.github.vincentvibe3.anilist.types

import kotlinx.serialization.Serializable

@Serializable
enum class MediaListStatus {
    CURRENT,
    PLANNING,
    COMPLETED,
    DROPPED,
    PAUSED,
    REPEATING
}
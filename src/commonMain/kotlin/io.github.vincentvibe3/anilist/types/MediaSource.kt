package io.github.vincentvibe3.anilist.types

import kotlinx.serialization.Serializable

@Serializable
enum class MediaSource {
    ORIGINAL, MANGA, LIGHT_NOVEL,
    VISUAL_NOVEL, VIDEO_GAME, OTHER,
    NOVEL, DOUJINSHI, ANIME, WEB_NOVEL, LIVE_ACTION,
    GAME, COMIC, MULTIMEDIA_PROJECT, PICTURE_BOOK
}
package io.github.vincentvibe3.anitraklib.anilist.types

import kotlinx.serialization.Serializable

@Serializable
enum class MediaFormat {
    TV, TV_SHORT, MOVIE, SPECIAL,
    OVA, ONA, MUSIC, MANGA, NOVEL, ONE_SHOT
}
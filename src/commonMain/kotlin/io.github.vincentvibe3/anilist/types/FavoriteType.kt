package io.github.vincentvibe3.anilist.types

import kotlinx.serialization.Serializable

@Serializable
enum class FavoriteType {
    ANIME, MANGA, CHARACTER, STAFF, STUDIO
}
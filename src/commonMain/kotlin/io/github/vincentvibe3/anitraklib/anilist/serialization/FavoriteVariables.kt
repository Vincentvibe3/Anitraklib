package io.github.vincentvibe3.anitraklib.anilist.serialization

import kotlinx.serialization.Serializable

@Serializable
data class FavoriteVariables(
    val animeId: Int? = null,
    val mangaId: Int? = null,
    val staffId: Int? = null,
    val characterId: Int? = null,
    val studioId: Int? = null
) {
    init {
        if (
            animeId == null &&
            mangaId == null &&
            staffId == null &&
            characterId == null &&
            studioId == null
        ) {
            throw IllegalStateException("Must specify an id for at least one category")
        }
    }
}
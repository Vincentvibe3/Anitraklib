package io.github.vincentvibe3.anilist.serialization

import io.github.vincentvibe3.anilist.types.Favourites
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FavoriteResult(
    @SerialName("ToggleFavourite")
    val toggleFavourite: Favourites
)
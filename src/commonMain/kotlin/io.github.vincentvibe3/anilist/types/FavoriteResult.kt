package io.github.vincentvibe3.anilist.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FavoriteResult(
    @SerialName("ToggleFavourite")
    val toggleFavourite:Favorites
)
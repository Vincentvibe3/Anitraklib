package io.github.vincentvibe3.anitraklib.anilist.types

import kotlinx.serialization.Serializable

@Serializable
data class Favourites(
    val anime: MediaConnection? = null,
    val manga: MediaConnection? = null,
    val characters: CharacterConnection? = null,
    val staff: StaffConnection? = null,
    val studios: StudioConnection? = null
)
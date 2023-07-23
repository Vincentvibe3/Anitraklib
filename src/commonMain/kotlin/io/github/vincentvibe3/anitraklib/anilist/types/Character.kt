package io.github.vincentvibe3.anitraklib.anilist.types

import io.github.vincentvibe3.anitraklib.anilist.serialization.FuzzyDateInt
import kotlinx.serialization.Serializable

@Serializable
data class Character(
    val id: Int,
    val name: CharacterName? = null,
    val image: CharacterImage? = null,
    val description: String? = null,
    val gender: String? = null,
    val dateOfBirth: FuzzyDateInt? = null,
    val age: String? = null,
    val bloodType: String? = null,
    val isFavourite: Boolean? = null,
    val isFavouriteBlocked: Boolean? = null,
    val siteUrl: String? = null,
    val media: MediaConnection? = null,
    val favourites: Int? = null,
    val modNotes: String? = null,
) {
    @Serializable
    data class CharacterImage(
        val large: String,
        val medium: String
    )

    @Serializable
    data class CharacterName(
        val first: String?,
        val middle: String?,
        val last: String?,
        val full: String?,
        val native: String?,
        val alternative: List<String>? = null,
        val alternativeSpoiler: List<String>? = null,
        val userPreferred: String?
    )
}

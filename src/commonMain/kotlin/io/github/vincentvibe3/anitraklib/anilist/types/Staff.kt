package io.github.vincentvibe3.anitraklib.anilist.types

import io.github.vincentvibe3.anitraklib.anilist.serialization.AnilistUser
import io.github.vincentvibe3.anitraklib.anilist.serialization.FuzzyDateInt
import kotlinx.serialization.Serializable

@Serializable
data class Staff(
    val id: Int,
    val name: StaffName,
    val languageV2: String? = null,
    val image: StaffImage? = null,
    val description: String? = null,
    val primaryOccupations: List<String>? = null,
    val gender: String? = null,
    val dateOfBirth: FuzzyDateInt? = null,
    val dateOfDeath: FuzzyDateInt? = null,
    val age: Int? = null,
    val yearsActive: List<Int>? = null,
    val homeTown: String? = null,
    val bloodType: String? = null,
    val isFavourite: Boolean? = null,
    val isFavouriteBlocked: Boolean? = null,
    val siteUrl: String? = null,
    val staffMedia: MediaConnection? = null,
    val characters: CharacterConnection? = null,
    val characterMedia: MediaConnection? = null,
//    val staff: Staff?=null,
    val submitter: AnilistUser? = null,
    val submissionStatus: Int? = null,
    val submissionNotes: String? = null,
    val favourites: Int? = null,
    val modNotes: String? = null
) {
    @Serializable
    data class StaffImage(
        val large: String,
        val medium: String
    )

    @Serializable
    data class StaffName(
        val first: String?,
        val middle: String?,
        val last: String?,
        val full: String?,
        val native: String?,
        val alternative: List<String>? = null,
        val userPreferred: String? = null
    )
}
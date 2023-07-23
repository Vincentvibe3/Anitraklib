package io.github.vincentvibe3.anitraklib.anilist.types

import io.github.vincentvibe3.anitraklib.anilist.serialization.AnilistUser
import io.github.vincentvibe3.anitraklib.anilist.serialization.FuzzyDateInt
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class MediaList(
    val id: Int? = null,
    val userId: Int? = null,
    val mediaId: Int? = null,
    val status: MediaListStatus? = null,
    val score: Float? = null,
    val progress: Int? = null,
    val progressVolumes: Int? = null,
    val repeat: Int? = null,
    val priority: Int? = null,
    val private: Boolean? = null,
    val notes: String? = null,
    val hiddenFromStatusLists: Boolean? = null,
    val customLists: JsonObject? = null,
    val advancedScores: JsonObject? = null,
    val startedAt: FuzzyDateInt? = null,
    val completedAt: FuzzyDateInt? = null,
    val updatedAt: Int? = null,
    val createdAt: Int? = null,
    val media: Media? = null,
    val user: AnilistUser? = null
)
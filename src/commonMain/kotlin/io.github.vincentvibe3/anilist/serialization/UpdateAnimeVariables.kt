package io.github.vincentvibe3.anilist.serialization

import io.github.vincentvibe3.anilist.types.MediaListStatus
import kotlinx.serialization.Serializable

@Serializable
data class UpdateAnimeVariables(
    val mediaId: Int,
    val status: MediaListStatus? = null,
    val score: Float? = null,
    val scoreRaw: Int? = null,
    val progress: Int? = null,
    val progressVolumes: Int? = null,
    val repeat: Int? = null,
    val priority: Int? = null,
    val private: Boolean? = null,
    val notes: String? = null,
    val hiddenFromStatusLists: Boolean? = null,
    val customLists: List<String>? = null,
    val advancedScores: List<Float>? = null,
    val startedAt: FuzzyDateInt? = null,
    val completedAt: FuzzyDateInt? = null,
){
}
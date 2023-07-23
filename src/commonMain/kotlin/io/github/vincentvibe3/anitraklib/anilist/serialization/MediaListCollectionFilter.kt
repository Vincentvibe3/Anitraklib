package io.github.vincentvibe3.anitraklib.anilist.serialization

import io.github.vincentvibe3.anitraklib.anilist.types.MediaListSort
import io.github.vincentvibe3.anitraklib.anilist.types.MediaListStatus
import io.github.vincentvibe3.anitraklib.anilist.types.MediaType
import kotlinx.serialization.Serializable

@Serializable
data class MediaListCollectionFilter(
    val userId: Int? = null,
    val userName: String? = null,
    val type: MediaType,
    val status: MediaListStatus? = null,
    val notes: String? = null,
    val startedAt: FuzzyDateInt? = null,
    val completedAt: FuzzyDateInt? = null,
    val chunk: Int? = null,
    val forceSingleCompletedList: Boolean? = null,
    val perChunk: Int? = null,
    val status_in: List<MediaListStatus>? = null,
    val status_not_in: List<MediaListStatus>? = null,
    val status_not: MediaListStatus? = null,
    val notes_like: String? = null,
    val startedAt_greater: FuzzyDateInt? = null,
    val startedAt_lesser: FuzzyDateInt? = null,
    val startedAt_like: String? = null,
    val completedAt_greater: FuzzyDateInt? = null,
    val completedAt_lesser: FuzzyDateInt? = null,
    val completedAt_like: String? = null,
    val sort: List<MediaListSort>? = null
)
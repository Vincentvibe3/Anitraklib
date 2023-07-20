package io.github.vincentvibe3.anilist.serialization

import io.github.vincentvibe3.anilist.types.MediaListSort
import io.github.vincentvibe3.anilist.types.MediaListStatus
import io.github.vincentvibe3.anilist.types.MediaType
import kotlinx.serialization.Serializable

@Serializable
data class MediaListFilter(
    val id:Int,
    val userId:Int,
    val userName:String,
    val type:MediaType,
    val mediaId:Int,
    val isFollowing:Boolean,
    val notes:String,
    val startedAt:FuzzyDateInt,
    val completedAt:FuzzyDateInt,
    val compareWithAuthList:Boolean,
    val userId_in:List<Int>,
    val status_in:List<MediaListStatus>,
    val status_not_in:List<MediaListStatus>,
    val status_not:MediaListStatus,
    val mediaId_in:List<Int>,
    val mediaId_not_in:List<Int>,
    val notes_like:String,
    val startedAt_greater:FuzzyDateInt,
    val startedAt_lesser:FuzzyDateInt,
    val startedAt_like:String,
    val completedAt_greater:FuzzyDateInt,
    val completedAt_lesser:FuzzyDateInt,
    val completedAt_like:String,
    val sort:List<MediaListSort>
) {
}
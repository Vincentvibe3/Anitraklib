package io.github.vincentvibe3.anilist.types

import io.github.vincentvibe3.anilist.serialization.AnilistUser
import kotlinx.serialization.Serializable

@Serializable
data class Review(
    val id:Int,
    val userId:Int?=null,
    val mediaId:Int?=null,
    val mediaType:MediaType?=null,
    val summary:String?=null,
    val body:String?=null,
    val rating:Int?=null,
    val ratingAmount:Int?=null,
    val userRating:ReviewRating?=null,
    val score:Int?=null,
    val private:Boolean?=null,
    val siteUrl:String?=null,
    val createdAt:Int?=null,
    val updatedAt:Int?=null,
    val user:AnilistUser?=null,
    val media:Media?=null
) {
    @Serializable
    enum class ReviewRating {
        NO_VOTE, UP_VOTE, DOWN_VOTE
    }
}

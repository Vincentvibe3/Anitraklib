package io.github.vincentvibe3.anilist.types

import AiringSchedule
import io.github.vincentvibe3.anilist.serialization.FuzzyDateInt
import kotlinx.serialization.Serializable

@Serializable
data class Media(
    val id:Int?,
    val idMal:Int?=null,
    val title:MediaTitle?,
    val synonyms:List<String>?=null,
    val averageScore:Int?=null,
    val meanScore:Int?=null,
    val popularity:Int?=null,
    val isLocked:Boolean?=null,
    val trending:Int?=null,
    val favourites: Int?=null,
    val tags:List<MediaTag>?=null,
    val characters:CharacterConnection?=null,
    val relations:MediaConnection?=null,
    val staff: StaffConnection?=null,
    val studios:StudioConnection?=null,
    val isFavouriteBlocked:Boolean?=null,
    val type:MediaType?=null,
    val format:MediaFormat?=null,
    val status:MediaStatus?=null,
    val description:String?=null,
    val startDate:FuzzyDateInt?=null,
    val endDate:FuzzyDateInt?=null,
    val season:MediaSeason?=null,
    val seasonYear:Int?=null,
    val seasonInt:Int?=null,
    val episodes:Int?=null,
    val duration:Int?=null,
    val chapters:Int?=null,
    val volumes:Int?=null,
    val countryOfOrigin:String?=null,
    val isLicensed:Boolean?=null,
    val source:MediaSource?=null,
    val hashtag:String?=null,
    val trailer:MediaTrailer?=null,
    val updatedAt:Int?=null,
    val coverImage:MediaCoverImage?=null,
    val bannerImage:String?=null,
    val genres:List<String>?=null,
    val isFavourite:Boolean?=null,
    val isAdult:Boolean?=null,
    val nextAiringEpisode:AiringSchedule?=null,
    val airingSchedule:AiringScheduleConnection?=null,
    val trends:MediaTrendConnection?=null,
    val externalLinks:List<MediaExternalLink>?=null,
    val streamingEpisodes:List<MediaStreamingEpisode>?=null,
    val rankings:List<MediaRank>?=null,
    val mediaListEntry:MediaList?=null,
    val reviews:ReviewConnection?=null,
    val recommendations:RecommendationConnection?=null,
    val stats:MediaStats?=null,
    val siteUrl:String?=null,
    val autoCreateForumThread:Boolean?=null,
    val isRecommendationBlocked:Boolean?=null,
    val isReviewBlocked:Boolean?=null,
    val modNotes:String?=null
){

    @Serializable
    data class MediaStats(
        val scoreDistribution:List<ScoreDistribution>,
        val statusDistribution:List<StatusDistribution>
    )

    @Serializable
    data class MediaRank(
        val id:Int,
        val rank:Int,
        val type:MediaRankType,
        val format:MediaFormat,
        val year:Int?,
        val season:MediaSeason?,
        val allTime:Boolean,
        val context:String?=null
    ){

        @Serializable
        enum class MediaRankType{
            RATED, POPULAR
        }

    }

    @Serializable
    data class MediaStreamingEpisode(
        val title:String,
        val thumbnail: String,
        val url:String,
        val site:String
    )

    @Serializable
    data class MediaExternalLink(
        val id:Int?=null,
        val url:String,
        val site:String,
        val siteId:Int?=null,
        val type:ExternalLinkType,
        val language:String?=null,
        val color:String?=null,
        val icon:String?=null,
        val notes :String?=null,
        val isDisabled:Boolean?=null
    )

    @Serializable
    data class MediaCoverImage(
        val extraLarge:String,
        val large:String,
        val medium:String,
        val color:String
    )

    @Serializable
    data class MediaTitle(
        val romaji:String?=null,
        val english:String?=null,
        val native:String?=null,
        val userPreferred:String?=null
    )

    @Serializable
    data class MediaTrailer(
        val id:String,
        val site:String,
        val thumbnail:String
    )
}
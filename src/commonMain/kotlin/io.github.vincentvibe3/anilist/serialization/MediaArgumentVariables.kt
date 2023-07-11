package io.github.vincentvibe3.anilist.serialization

import io.github.vincentvibe3.anilist.types.*

data class MediaArgumentVariables(
    val mediaId: Int?=null,
    val idMal:Int?=null,
    val startDate:Int?=null,
    val endDateInput: Int?=null,
    val season: MediaSeason?=null,
    val seasonYear:Int?=null,
    val type: MediaType?=null,
    val format: MediaFormat?=null,
    val status: MediaStatus?=null,
    val episodes:Int?=null,
    val duration:Int?=null,
    val chapters:Int?=null,
    val volumes:Int?=null,
    val isAdult:Boolean?=null,
    val genre:String?=null,
    val tag:String?=null,
    val minimumTagRank:Int?=null,
    val tagCategory:String?=null,
    val onList:Boolean?=null,
    val licensedBy:String?=null,
    val licensedById: String?=null,
    val averageScore: Int?=null,
    val popularity: Int?=null,
    val source: MediaSource?=null,
    val countryOfOrigin:String?=null,
    val isLicensed:Boolean?=null,
    val search:String?=null,
    val id_not:Int?=null,
    val id_in:List<Int>?=null,
    val id_not_in:List<Int>?=null,
    val idMal_in:List<Int>?=null,
    val idMal_not_in:List<Int>?=null,
    val startDate_greater: Int?=null,
    val startDate_lesser: Int?=null,
    val startDate_like:Int?=null,
    val endDate_greater:Int?=null,
    val endDate_lesser:Int?=null,
    val endDate_like:String?=null,
    val format_in: MediaFormat?=null,
    val format_not: MediaFormat?=null,
    val format_not_in: MediaFormat?=null,
    val status_in: MediaStatus?=null,
    val status_not: MediaStatus?=null,
    val status_not_in:List<MediaStatus>?=null,
    val episodes_greater:Int?=null,
    val duration_greater:Int?=null,
    val duration_lesser:Int?=null,
    val chapters_greater:Int?=null,
    val chapters_lesser:Int?=null,
    val volumes_greater:Int?=null,
    val volumes_lesser:Int?=null,
    val genre_in:List<String>?=null,
    val genre_not_in:List<String>?=null,
    val tag_in:List<String>?=null,
    val tag_not_in:List<String>?=null,
    val tagCategory_in: List<String>?=null,
    val tagCategory_not_in: List<String>?=null,
    val licensedById_in:List<Int>?=null,
    val averageScore_not: Int?=null,
    val averageScore_greater:Int?=null,
    val averageScore_lesser: Int?=null,
    val popularity_not: Int?=null,
    val popularity_greater: Int?=null,
    val popularity_lesser:Int?=null,
    val source_in:List<MediaSource>?=null,
    val sort: MediaSort?=null
) {
    init{
        if (
            mediaId==null&&
            idMal==null&&
            startDate==null&&
            endDateInput==null&&
            season==null&&
            seasonYear==null&&
            type==null&&
            format==null&&
            status==null&&
            episodes==null&&
            duration==null&&
            chapters==null&&
            volumes==null&&
            isAdult==null&&
            genre==null&&
            tag==null&&
            minimumTagRank==null&&
            tagCategory==null&&
            onList==null&&
            licensedBy==null&&
            licensedById==null&&
            averageScore==null&&
            popularity==null&&
            source==null&&
            countryOfOrigin==null&&
            isLicensed==null&&
            search==null&&
            id_not==null&&
            id_in==null&&
            id_not_in==null&&
            idMal_in==null&&
            idMal_not_in==null&&
            startDate_greater==null&&
            startDate_lesser==null&&
            startDate_like==null&&
            endDate_greater==null&&
            endDate_lesser==null&&
            endDate_like==null&&
            format_in==null&&
            format_not==null&&
            format_not_in==null&&
            status_in==null&&
            status_not==null&&
            status_not_in==null&&
            episodes_greater==null&&
            duration_greater==null&&
            duration_lesser==null&&
            chapters_greater==null&&
            chapters_lesser==null&&
            volumes_greater==null&&
            volumes_lesser==null&&
            genre_in==null&&
            genre_not_in==null&&
            tag_in==null&&
            tag_not_in==null&&
            tagCategory_in==null&&
            tagCategory_not_in==null&&
            licensedById_in==null&&
            averageScore_not==null&&
            averageScore_greater==null&&
            averageScore_lesser==null&&
            popularity_not==null&&
            popularity_greater==null&&
            popularity_lesser==null&&
            source_in==null&&
            sort==null
        ){
            throw IllegalStateException("MediaArgumentVariables may not be empty")
        }
    }

}
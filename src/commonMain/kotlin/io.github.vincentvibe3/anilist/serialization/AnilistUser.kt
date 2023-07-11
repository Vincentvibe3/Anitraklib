package io.github.vincentvibe3.anilist.serialization

import kotlinx.serialization.Serializable

@Serializable
data class AnilistUser(
    val id:Int,
    val name:String,
    val avatar:UserAvatar,
    val mediaListOptions: MediaListOptions
) {

    @Serializable
    data class MediaListOptions(
        val animeList:MediaListTypeOption,
        val mangaList:MediaListTypeOption,
        val scoreFormat:String
    )

    @Serializable
    data class MediaListTypeOption(
        val customLists:List<String>
    )

    @Serializable
    data class UserAvatar(
        val medium:String?=null,
        val large:String?=null
    )
}
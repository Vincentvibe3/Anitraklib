package io.github.vincentvibe3.anilist.serialization

import io.github.vincentvibe3.anilist.types.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class AnilistUser(
    val id:Int,
    val name:String,
    val about:String,
    val avatar:UserAvatar,
    val bannerImage:String,
    val isFollowing:Boolean,
    val isFollower:Boolean,
    val isBlocked:Boolean,
    val bans:JsonObject,
    val options:UserOptions,
    val favourites: Favourites,
    val statistics:UserStatisticTypes,
    val unreadNotificationCount:Int,
    val siteUrl:String,
    val donatorTier:Int,
    val donatorBadge:String,
    val moderatorRoles:ModRole,
    val createdAt:Int,
    val updatesAt:Int,
    val previousNames:List<UserPreviousName>,
    val mediaListOptions: MediaListOptions
) {

    @Serializable
    enum class UserTitleLanguage{
        ROMAJI, ENGLISH, NATIVE, ROMAJI_STYLISED, ENGLISH_STYLISED, NATIVE_STYLISED
    }

    @Serializable
    data class UserOptions(
        val titleLanguage:UserTitleLanguage,
        val displayAdultContent:Boolean,
        val airingNotifications:Boolean,
        val profileColor:String,
        val notificationOprions:List<NotificationOption>,
        val timezone:String,
        val activityMergeTime:Int,
        val staffNameLanguage:UserStaffNameLanguage,
        val restrictMessagesToFollowing:Boolean,
        val disabledListActivity:List<ListActivityOption>
    )

    @Serializable
    data class ListActivityOption(
        val disabled:Boolean,
        val type:MediaListStatus
    )

    @Serializable
    enum class UserStaffNameLanguage{
        ROMAJI_WESTERN, ROMAJI, NATIVE
    }

    @Serializable
    data class NotificationOption(
        val type:NotificationType,
        val enabled:Boolean
    )

    @Serializable
    enum class NotificationType{
        ACTIVITY_MESSAGE, ACTIVITY_REPLY, FOLLOWING, ACTIVITY_MENTION, THREAD_COMMENT_MENTION,
        THREAD_SUBSCRIBED, THREAD_COMMENT_REPLY, AIRING, ACTIVITY_LIKE, ACTIVITY_REPLY_LIKE,
        THREAD_LIKE, THREAD_COMMENT_LIKE, ACTIVITY_REPLY_SUBSCRIBED, RELATED_MEDIA_ADDITION,
        MEDIA_DATA_CHANGE, MEDIA_MERGE, MEDIA_DELETION
    }

    @Serializable
    data class UserStatisticTypes(
        val anime: UserStatistics,
        val manga: UserStatistics
    )

    @Serializable
    data class UserStatistics(
        val count: Int,
        val meanScore: Float,
        val standardDeviation: Float,
        val minutesWatched: Int,
        val episodesWatched: Int,
        val chaptersRead: Int,
        val volumesRead: Int,
        val formats: UserFormatStatistic,
        val statuses: UserStatusStatistic,
        val scores: UserScoreStatistic,
        val lengths: UserLengthStatistic,
        val releaseYears: UserReleaseYearStatistic,
        val startYears: UserStartYearStatistic,
        val genres: UserGenreStatistic,
        val tags: UserTagStatistic,
        val countries: UserCountryStatistic,
        val voiceActors: UserVoiceActorStatistic,
        val staff: UserStaffStatistic,
        val studios: UserStudioStatistic
    )

    @Serializable
    sealed class UserStatistic {
        abstract val count: Int
        abstract val meanScore: Float
        abstract val minutesWatched: Int
        abstract val chaptersRead: Int
        abstract val mediaIds: List<Int>
    }

    @Serializable
    data class UserFormatStatistic(
        val format:MediaFormat,
        override val count: Int,
        override val meanScore: Float,
        override val minutesWatched: Int,
        override val chaptersRead: Int,
        override val mediaIds: List<Int>
    ):UserStatistic()

    @Serializable
    data class UserStatusStatistic(
        val status:MediaListStatus,
        override val count: Int,
        override val meanScore: Float,
        override val minutesWatched: Int,
        override val chaptersRead: Int,
        override val mediaIds: List<Int>
    ):UserStatistic()

    @Serializable
    data class UserScoreStatistic(
        val score:Int,
        override val count: Int,
        override val meanScore: Float,
        override val minutesWatched: Int,
        override val chaptersRead: Int,
        override val mediaIds: List<Int>
    ):UserStatistic()

    @Serializable
    data class UserLengthStatistic(
        val length:String,
        override val count: Int,
        override val meanScore: Float,
        override val minutesWatched: Int,
        override val chaptersRead: Int,
        override val mediaIds: List<Int>
    ):UserStatistic()

    @Serializable
    data class UserReleaseYearStatistic(
        val releaseYear:Int,
        override val count: Int,
        override val meanScore: Float,
        override val minutesWatched: Int,
        override val chaptersRead: Int,
        override val mediaIds: List<Int>
    ):UserStatistic()

    @Serializable
    data class UserStartYearStatistic(
        val startYear:Int,
        override val count: Int,
        override val meanScore: Float,
        override val minutesWatched: Int,
        override val chaptersRead: Int,
        override val mediaIds: List<Int>
    ):UserStatistic()

    @Serializable
    data class UserGenreStatistic(
        val genre:String,
        override val count: Int,
        override val meanScore: Float,
        override val minutesWatched: Int,
        override val chaptersRead: Int,
        override val mediaIds: List<Int>
    ):UserStatistic()

    @Serializable
    data class UserTagStatistic(
        val tag:MediaTag,
        override val count: Int,
        override val meanScore: Float,
        override val minutesWatched: Int,
        override val chaptersRead: Int,
        override val mediaIds: List<Int>
    ):UserStatistic()

    @Serializable
    data class UserCountryStatistic(
        val country:String,
        override val count: Int,
        override val meanScore: Float,
        override val minutesWatched: Int,
        override val chaptersRead: Int,
        override val mediaIds: List<Int>
    ):UserStatistic()

    @Serializable
    data class UserVoiceActorStatistic(
        val voiceActor:Staff,
        val characterIds:List<Int>,
        override val count: Int,
        override val meanScore: Float,
        override val minutesWatched: Int,
        override val chaptersRead: Int,
        override val mediaIds: List<Int>
    ):UserStatistic()

    @Serializable
    data class UserStaffStatistic(
        val staff: Staff,
        override val count: Int,
        override val meanScore: Float,
        override val minutesWatched: Int,
        override val chaptersRead: Int,
        override val mediaIds: List<Int>
    ):UserStatistic()

    @Serializable
    data class UserStudioStatistic(
        val studio: Studio,
        override val count: Int,
        override val meanScore: Float,
        override val minutesWatched: Int,
        override val chaptersRead: Int,
        override val mediaIds: List<Int>
    ):UserStatistic()

    @Serializable
    enum class ModRole{
        ADMIN, LEAD_DEVELOPER, DEVELOPER, LEAD_COMMUNITY,
        COMMUNITY, DISCORD_COMMUNITY, LEAD_ANIME_DATA,
        ANIME_DATA, LEAD_MANGA_DATA, MANGA_DATA, LEAD_SOCIAL_MEDIA,
        SOCIAL_MEDIA, RETIRED
    }

    @Serializable
    data class UserPreviousName(
        val name:String,
        val createdAt: Int,
        val updatedAt: Int
    )

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
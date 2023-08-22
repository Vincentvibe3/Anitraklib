package io.github.vincentvibe3.anitraklib.anilist

import io.github.vincentvibe3.anitraklib.RateLimiting
import io.github.vincentvibe3.anitraklib.anilist.serialization.*
import io.github.vincentvibe3.anitraklib.anilist.types.*
import io.github.vincentvibe3.gqlclient.dsl.*
import io.github.vincentvibe3.gqlclient.http.GQLClient
import io.github.vincentvibe3.gqlclient.http.HttpHeader
import kotlinx.serialization.json.*
import kotlin.reflect.KProperty

class Anilist {

    var token = ""
    var id = ""
    val oAuthEndpoint by OAuthDelegate()

    class OAuthDelegate {
        operator fun getValue(anilist: Anilist, property: KProperty<*>): Any {
            return "https://anilist.co/api/v2/oauth/authorize?client_id=${anilist.id}&response_type=token"
        }
    }

    companion object {
        val gqlclient = GQLClient()
        const val apiEndpoint = "https://graphql.anilist.co"
    }

    private suspend inline fun <reified T> send(
        query: Query? = null,
        mutation: Mutation? = null,
        variables: JsonObject = buildJsonObject { },
        needAuth: Boolean = true
    ): T {
        val headers = if (needAuth) {
            listOf(
                HttpHeader("Authorization", "Bearer $token")
            )
        } else {
            listOf()
        }
        val result = RateLimiting.execute {
            val result = if (mutation != null) {
                gqlclient.sendMutation<T, AnilistGQLError>(
                    apiEndpoint, mutation, variables, headers = headers
                )
            } else if (query != null) {
                gqlclient.sendQuery<T, AnilistGQLError>(
                    apiEndpoint, query, variables, headers = headers
                )
            } else {
                null
            }
            result?.httpResponse to result
        }

        return if (result != null) {
            val errors = result.errors
            if (errors != null){
                throw AnilistRequestException(errors)
            }
            result.data ?: throw AnilistRequestException(result.errors!!)
        } else {
            throw IllegalStateException("You must pass either a query or mutation")
        }
    }

    suspend fun getCurrentUser(): AnilistUser {
        val query = query {
            field("Viewer") {
                field("id")
                field("name")
                field("avatar") {
                    field("medium")
                }
                field("mediaListOptions") {
                    field("scoreFormat")
                    field("animeList") {
                        field("customLists")
                    }
                    field("mangaList") {
                        field("customLists")
                    }
                }
            }
        }
        return send<ViewerResult>(query).Viewer
    }

    suspend fun updateProgress(mediaId: Int, progress: Int): ProgressUpdateResult.ProgressUpdate {
        val mutation = mutation {
            field("SaveMediaListEntry") {
                addArg("mediaId", Variable("mediaId", "Int"))
                addArg("progress", Variable("progress", "Int"))
                field("userId")
                field("id")
                field("progress")
                field("mediaId")
            }
        }
        val variables = buildJsonObject {
            put("mediaId", mediaId)
            put("progress", progress)
        }
        return send<ProgressUpdateResult>(mutation = mutation, variables = variables).saveMediaListEntry
    }

    suspend fun updateAnime(
        mediaId: Int,
        status: MediaListStatus? = null,
        score: Float? = null,
        scoreRaw: Int? = null,
        progress: Int? = null,
        progressVolumes: Int? = null,
        repeat: Int? = null,
        priority: Int? = null,
        private: Boolean? = null,
        notes: String? = null,
        hiddenFromStatusLists: Boolean? = null,
        customLists: List<String>? = null,
        advancedScores: List<Float>? = null,
        startedAt: FuzzyDateInt? = null,
        completedAt: FuzzyDateInt? = null,
    ): MediaList? {
        val mutation = mutation {
            field("SaveMediaListEntry") {
                addArg("mediaId", Variable("mediaId", "Int"))
                addArg("status", Variable("status", "MediaListStatus"))
                addArg("progress", Variable("progress", "Int"))
                addArg("score", Variable("score", "Float"))
                addArg("scoreRaw", Variable("scoreRaw", "Int"))
                addArg("progressVolumes", Variable("progressVolumes", "Int"))
                addArg("repeat", Variable("repeat", "Int"))
                addArg("priority", Variable("priority", "Int"))
                addArg("private", Variable("private", "Boolean"))
                addArg("notes", Variable("notes", "String"))
                addArg("hiddenFromStatusLists", Variable("hiddenFromStatusLists", "Boolean"))
                addArg("customLists", Variable("customLists", "[String]"))
                addArg("advancedScores", Variable("advancedScores", "[Float]"))
                addArg(
                    "startedAt",
                    Variable("startedAt", "FuzzyDateInput")
                )
                addArg(
                    "completedAt",
                    Variable("completedAt", "FuzzyDateInput")
                )
                field("userId")
                field("id")
                field("progress")
                field("mediaId")
            }
        }
        val variables = Json.encodeToJsonElement(
            UpdateAnimeVariables(
                mediaId,
                status,
                score,
                scoreRaw,
                progress,
                progressVolumes,
                repeat,
                priority,
                private,
                notes,
                hiddenFromStatusLists,
                customLists,
                advancedScores,
                startedAt,
                completedAt
            )
        ).jsonObject
        return send<UpdateAnimeResult>(mutation = mutation, variables = variables).SaveMediaListEntry
    }

    suspend fun favorite(id: Int, entryType: FavoriteType): Favourites {
        val mutation = mutation {
            field("ToggleFavourite") {
                addArg("animeId", Variable("animeId", "Int"))
                addArg("mangaId", Variable("mangaId", "Int"))
                addArg("characterId", Variable("characterId", "Int"))
                addArg("staffId", Variable("staffId", "Int"))
                addArg("studioId", Variable("studioId", "Int"))
                field("anime") {
                    skip(entryType != FavoriteType.ANIME)
                    field("nodes") {
                        field("id")
                        field("isFavourite")
                    }
                }
                field("manga") {
                    skip(entryType != FavoriteType.MANGA)
                    field("nodes") {
                        field("id")
                        field("isFavourite")
                    }
                }
                field("characters") {
                    skip(entryType != FavoriteType.CHARACTER)
                    field("nodes") {
                        field("id")
                        field("isFavourite")
                    }
                }
                field("staff") {
                    skip(entryType != FavoriteType.STAFF)
                    field("nodes") {
                        field("id")
                        field("isFavourite")
                    }
                }
                field("studios") {
                    skip(entryType != FavoriteType.STUDIO)
                    field("nodes") {
                        field("id")
                        field("isFavourite")
                    }
                }
            }
        }
        val variablesObject = when (entryType) {
            FavoriteType.ANIME -> FavoriteVariables(animeId = id)
            FavoriteType.MANGA -> FavoriteVariables(mangaId = id)
            FavoriteType.CHARACTER -> FavoriteVariables(characterId = id)
            FavoriteType.STAFF -> FavoriteVariables(staffId = id)
            FavoriteType.STUDIO -> FavoriteVariables(studioId = id)
        }
        val variables = Json.encodeToJsonElement(variablesObject).jsonObject
        return send<FavoriteResult>(mutation = mutation, variables = variables).toggleFavourite
    }

    //Deletes a show, id is not the media
    suspend fun deleteMedia(id: Int): Boolean {
        val mutation = mutation {
            field("DeleteMediaListEntry") {
                addArg("id", Variable("id", "Int"))
                field("deleted")
            }
        }
        val variables = buildJsonObject {
            put("id", id)
        }
        return send<JsonObject>(mutation = mutation, variables = variables).jsonPrimitive.boolean
    }

    suspend fun page(page: Int, perPage: Int) {

    }

    suspend fun getMedia(
        mediaId: Int,
        idMal: Int? = null,
        startDate: Int? = null,
        endDateInput: Int? = null,
        season: MediaSeason? = null,
        seasonYear: Int? = null,
        type: MediaType? = null,
        format: MediaFormat? = null,
        status: MediaStatus? = null,
        episodes: Int? = null,
        duration: Int? = null,
        chapters: Int? = null,
        volumes: Int? = null,
        isAdult: Boolean? = null,
        genre: String? = null,
        tag: String? = null,
        minimumTagRank: Int? = null,
        tagCategory: String? = null,
        onList: Boolean? = null,
        licensedBy: String? = null,
        licensedById: String? = null,
        averageScore: Int? = null,
        popularity: Int? = null,
        source: MediaSource? = null,
        countryOfOrigin: String? = null,
        isLicensed: Boolean? = null,
        search: String? = null,
        id_not: Int? = null,
        id_in: List<Int>? = null,
        id_not_in: List<Int>? = null,
        idMal_in: List<Int>? = null,
        idMal_not_in: List<Int>? = null,
        startDate_greater: Int? = null,
        startDate_lesser: Int? = null,
        startDate_like: Int? = null,
        endDate_greater: Int? = null,
        endDate_lesser: Int? = null,
        endDate_like: String? = null,
        format_in: MediaFormat? = null,
        format_not: MediaFormat? = null,
        format_not_in: MediaFormat? = null,
        status_in: MediaStatus? = null,
        status_not: MediaStatus? = null,
        status_not_in: List<MediaStatus>? = null,
        episodes_greater: Int? = null,
        duration_greater: Int? = null,
        duration_lesser: Int? = null,
        chapters_greater: Int? = null,
        chapters_lesser: Int? = null,
        volumes_greater: Int? = null,
        volumes_lesser: Int? = null,
        genre_in: List<String>? = null,
        genre_not_in: List<String>? = null,
        tag_in: List<String>? = null,
        tag_not_in: List<String>? = null,
        tagCategory_in: List<String>? = null,
        tagCategory_not_in: List<String>? = null,
        licensedById_in: List<Int>? = null,
        averageScore_not: Int? = null,
        averageScore_greater: Int? = null,
        averageScore_lesser: Int? = null,
        popularity_not: Int? = null,
        popularity_greater: Int? = null,
        popularity_lesser: Int? = null,
        source_in: List<MediaSource>? = null,
        sort: MediaSort? = null
    ): Media? {
        val query = query {
            field("Media") {
                addArg("id", Variable("id", "Int"))
                addArg("idMal", Variable("idMal", "Int"))
                addArg("startDate", Variable("startDate", "FuzzyDateInt"))
                addArg("endDate", Variable("endDate", "FuzzyDateInt"))
                addArg("season", Variable("season", "MediaSeason"))
                addArg("seasonYear", Variable("seasonYear", "Int"))
                addArg("type", Variable("type", "MediaType"))
                addArg("format", Variable("format", "MediaFormat"))
                addArg("status", Variable("status", "MediaStatus"))
                addArg("episodes", Variable("episodes", "Int"))
                addArg("duration", Variable("duration", "Int"))
                addArg("chapters", Variable("chapters", "Int"))
                addArg("volumes", Variable("volumes", "Int"))
                addArg("isAdult", Variable("isAdult", "Boolean"))
                addArg("genre", Variable("genre", "String"))
                addArg("tag", Variable("tag", "String"))
                addArg("minimumTagRank", Variable("minimumTagRank", "Int"))
                addArg("tagCategory", Variable("tagCategory", "String"))
                addArg("onList", Variable("onList", "Boolean"))
                addArg("licensedBy", Variable("licensedBy", "String"))
                addArg("licensedById", Variable("licensedById", "Int"))
                addArg("averageScore", Variable("averageScore", "Int"))
                addArg("popularity", Variable("popularity", "Int"))
                addArg("source", Variable("source", "MediaSource"))
                addArg("countryOfOrigin", Variable("countryOfOrigin", "CountryCode"))
                addArg("isLicensed", Variable("isLicensed", "Boolean"))
                addArg("search", Variable("search", "String"))
                addArg("id_not", Variable("id_not", "Int"))
                addArg("id_in", Variable("id_in", "[Int]"))
                addArg("id_not_in", Variable("id_not_in", "[Int]"))
                addArg("idMal_not", Variable("idMal_not", "Int"))
                addArg("idMal_in", Variable("idMal_in", "[Int]"))
                addArg("idMal_not_in", Variable("id_in", "[Int]"))
                addArg("startDate_greater", Variable("startDate_greater", "FuzzyDateInt"))
                addArg("startDate_lesser", Variable("startDate_lesser", "FuzzyDateInt"))
                addArg("startDate_like", Variable("startDate_like", "String"))
                addArg("endDate_greater", Variable("endDate_greater", "FuzzyDateInt"))
                addArg("endDate_lesser", Variable("endDate_lesser", "FuzzyDateInt"))
                addArg("endDate_like", Variable("endDate_like", "String"))
                addArg("format_in", Variable("format_in", "[MediaFormat]"))
                addArg("format_not", Variable("format_not", "MediaFormat"))
                addArg("format_not_in", Variable("format_not_in", "[MediaFormat]"))
                addArg("status_in", Variable("status_in", "[MediaStatus]"))
                addArg("status_not", Variable("status_not", " MediaStatus"))
                addArg("status_not_in", Variable("status_not_in", "[MediaStatus]"))
                addArg("episodes_greater", Variable("episodes_greater", "Int"))
                addArg("duration_greater", Variable("duration_greater", "Int"))
                addArg("duration_lesser", Variable("duration_lesser", "Int"))
                addArg("chapters_greater", Variable("chapters_greater", "Int"))
                addArg("chapters_lesser", Variable("chapters_lesser", "Int"))
                addArg("volumes_greater", Variable("volumes_greater", "Int"))
                addArg("volumes_lesser", Variable("volumes_lesser", "Int"))
                addArg("genre_in", Variable("genre_in", "[String]"))
                addArg("genre_not_in", Variable("genre_not_in", "[String]"))
                addArg("tag_in", Variable("tag_in", "[String]"))
                addArg("tag_not_in", Variable("tag_not_in", "[String]"))
                addArg("tagCategory_in", Variable("tagCategory_in", "[String]"))
                addArg("tagCategory_not_in", Variable("tagCategory_not_in", "[String]"))
                addArg("licensedById_in", Variable("licensedById_in", "[Int]"))
                addArg("averageScore_not", Variable("averageScore_not", " Int"))
                addArg("averageScore_greater", Variable("averageScore_greater", "Int"))
                addArg("averageScore_lesser", Variable("averageScore_lesser", " Int"))
                addArg("popularity_not", Variable("popularity_not", " Int"))
                addArg("popularity_greater", Variable("popularity_greater", " Int"))
                addArg("popularity_lesser", Variable("popularity_lesser", "Int"))
                addArg("source_in", Variable("source_in", "[MediaSource]"))
                addArg("sort", Variable("sort", "[MediaSort]"))
                field("id")
                field("idMal")
                field("title") {
                    field("romaji") {
                        addArg("stylised", "true")
                    }
                    field("english") {
                        addArg("stylised", "true")
                    }
                    field("native") {
                        addArg("stylised", "true")
                    }
                    field("userPreferred")
                }
                field("type")
                field("format")
                field("status")
                field("description")
                field("startDate") {
                    field("year")
                    field("month")
                    field("day")
                }
                field("endDate") {
                    field("year")
                    field("month")
                    field("day")
                }
                field("season")
                field("seasonYear")
                field("seasonInt")
                field("episodes")
                field("duration")
                field("chapters")
                field("volumes")
                field("countryOfOrigin")
                field("isLicensed")
                field("source")
                field("hashtag")
                field("trailer") {
                    field("id")
                    field("site")
                    field("thumbnail")
                }
                field("updatedAt")
                field("coverImage") {
                    field("extraLarge")
                    field("large")
                    field("medium")
                    field("color")
                }
                field("bannerImage")
                field("genres")
                field("synonyms")
                field("averageScore")
                field("meanScore")
                field("popularity")
                field("isLocked")
                field("trending")
                field("favourites")
                field("tags") {
                    field("id")
                    field("name")
                    field("isAdult")
                    field("isMediaSpoiler")
                    field("isGeneralSpoiler")
                    field("rank")
                    field("category")
                    field("description")
                }
                field("studios"){
                    field("nodes") {
                        field("id")
                        field("name")
                        field("isAnimationStudio")
                        field("siteUrl")
                        field("isFavourite")
                        field("favourites")
                    }
                    field("pageInfo") {
                        field("total")
                        field("perPage")
                        field("currentPage")
                        field("lastPage")
                        field("hasNextPage")
                    }
                }
                field("relations") {
                    field("edges"){
                        field("relationType")
                        field("id")
                        field("node"){
                            field("id")
                            field("title") {
                                field("romaji")
                                field("english")
                                field("native")
                                field("userPreferred")
                            }
                            field("format")
                            field("coverImage"){
                                field("medium")
                            }
                        }
                    }
                    field("nodes") {
                        field("id")
                        field("title") {
                            field("romaji")
                            field("english")
                            field("native")
                            field("userPreferred")
                        }
                    }
                    field("pageInfo") {
                        field("total")
                        field("perPage")
                        field("currentPage")
                        field("lastPage")
                        field("hasNextPage")
                    }
                }
                field("characters") {
                    field("edges"){
                        field("id")
                        field("node"){
                            field("id")
                            field("image"){
                                field("large")
                                field("medium")
                            }
                            field("name") {
                                field("first")
                                field("middle")
                                field("last")
                                field("full")
                                field("native")
                                field("userPreferred")
                            }
                        }
                        field("role")
                        field("name")
                        field("voiceActors"){
                            addArg("sort", "RELEVANCE")
                            field("id")
                            field("languageV2")
                            field("name") {
                                field("first")
                                field("middle")
                                field("last")
                                field("full")
                                field("native")
                                field("userPreferred")
                            }
                        }
                    }
                    field("nodes") {
                        field("id")
                        field("name") {
                            field("first")
                            field("middle")
                            field("last")
                            field("full")
                            field("native")
                            field("userPreferred")
                        }
                    }
                    field("pageInfo") {
                        field("total")
                        field("perPage")
                        field("currentPage")
                        field("lastPage")
                        field("hasNextPage")
                    }
                }
                field("staff") {
                    field("edges"){
                        field("id")
                        field("role")
                        field("node"){
                            field("id")
                            field("name") {
                                field("first")
                                field("middle")
                                field("last")
                                field("full")
                                field("native")
                                field("userPreferred")
                            }
                            field("image"){
                                field("medium")
                                field("large")
                            }
                        }
                    }
                    field("nodes") {
                        field("id")
                        field("name") {
                            field("first")
                            field("middle")
                            field("last")
                            field("full")
                            field("native")
                            field("userPreferred")
                        }
                    }
                    field("pageInfo") {
                        field("total")
                        field("perPage")
                        field("currentPage")
                        field("lastPage")
                        field("hasNextPage")
                    }
                }
                field("studios") {
                    field("nodes") {
                        field("id")
                        field("name")
                    }
                    field("pageInfo") {
                        field("total")
                        field("perPage")
                        field("currentPage")
                        field("lastPage")
                        field("hasNextPage")
                    }
                }
                field("isFavourite")
                field("isFavouriteBlocked")
                field("isAdult")
                field("nextAiringEpisode") {
                    field("id")
                    field("episode")
                    field("airingAt")
                }
                field("airingSchedule") {
                    field("nodes") {
                        field("id")
                        field("episode")
                        field("airingAt")
                        field("mediaId")
                    }
                    field("pageInfo") {
                        field("total")
                        field("perPage")
                        field("currentPage")
                        field("lastPage")
                        field("hasNextPage")
                    }
                }
                field("trends") {
                    field("nodes") {
                        field("averageScore")
                        field("popularity")
                        field("inProgress")
                        field("episode")
                    }
                    field("pageInfo") {
                        field("total")
                        field("perPage")
                        field("currentPage")
                        field("lastPage")
                        field("hasNextPage")
                    }
                }
                field("externalLinks") {
                    field("site")
                    field("url")
                    field("type")
                }
                field("streamingEpisodes") {
                    field("title")
                    field("thumbnail")
                    field("url")
                    field("site")
                }
                field("rankings") {
                    field("id")
                    field("allTime")
                    field("format")
                    field("type")
                    field("rank")
                    field("year")
                    field("season")

                }
                field("mediaListEntry") {
                    useFragment(Fragments.mediaListEntry)
                }
                field("reviews") {
                    field("nodes") {
                        field("id")
                    }
                    field("pageInfo") {
                        field("total")
                        field("perPage")
                        field("currentPage")
                        field("lastPage")
                        field("hasNextPage")
                    }
                }
                field("recommendations") {
                    field("nodes") {
                        field("id")
                        field("rating")
                        field("userRating")
                        field("mediaRecommendation") {
                            field("id")
                            field("title") {
                                field("romaji") {
                                    addArg("stylised", "true")
                                }
                                field("english") {
                                    addArg("stylised", "true")
                                }
                                field("native") {
                                    addArg("stylised", "true")
                                }
                                field("userPreferred")
                            }
                            field("coverImage"){
                                field("medium")
                                field("large")
                                field("extraLarge")
                                field("color")
                            }
                        }
                    }
                    field("pageInfo") {
                        field("total")
                        field("perPage")
                        field("currentPage")
                        field("lastPage")
                        field("hasNextPage")
                    }
                }
                field("stats") {
                    field("scoreDistribution") {
                        field("score")
                        field("amount")
                    }
                    field("statusDistribution") {
                        field("status")
                        field("amount")
                    }
                }
                field("siteUrl")
                field("autoCreateForumThread")
                field("isRecommendationBlocked")
                field("isReviewBlocked")
                field("modNotes")
            }
        }
        println(query.toString())
        val variables = Json.encodeToJsonElement(
            MediaVariables(
                mediaId,
                idMal,
                startDate,
                endDateInput,
                season,
                seasonYear,
                type,
                format,
                status,
                episodes,
                duration,
                chapters,
                volumes,
                isAdult,
                genre,
                tag,
                minimumTagRank,
                tagCategory,
                onList,
                licensedBy,
                licensedById,
                averageScore,
                popularity,
                source,
                countryOfOrigin,
                isLicensed,
                search,
                id_not,
                id_in,
                id_not_in,
                idMal_in,
                idMal_not_in,
                startDate_greater,
                startDate_lesser,
                startDate_like,
                endDate_greater,
                endDate_lesser,
                endDate_like,
                format_in,
                format_not,
                format_not_in,
                status_in,
                status_not,
                status_not_in,
                episodes_greater,
                duration_greater,
                duration_lesser,
                chapters_greater,
                chapters_lesser,
                volumes_greater,
                volumes_lesser,
                genre_in,
                genre_not_in,
                tag_in,
                tag_not_in,
                tagCategory_in,
                tagCategory_not_in,
                licensedById_in,
                averageScore_not,
                averageScore_greater,
                averageScore_lesser,
                popularity_not,
                popularity_greater,
                popularity_lesser,
                source_in,
                sort
            )
        ).jsonObject
        return send<MediaResult>(query, variables = variables).Media
    }

    suspend fun getMediaList(filter: MediaListCollectionFilter): MediaListCollection {
        val query = query {
            field("MediaListCollection") {
                addArg("userId", Variable("userId", "Int"))
                addArg("userName", Variable("userName", "String"))
                addArg("type", Variable("type", "MediaType"))
                addArg("status", Variable("status", "MediaListStatus"))
                addArg("notes", Variable("notes", "String"))
                addArg("startedAt", Variable("startedAt", "FuzzyDateInt"))
                addArg("completedAt", Variable("completedAt", "FuzzyDateInt"))
                addArg("forceSingleCompletedList", Variable("forceSingleCompletedList", "Boolean"))
                addArg("chunk", Variable("chunk", "Int"))
                addArg("perChunk", Variable("perChunk", "Int"))
                addArg("status_in", Variable("status_in", "[MediaListStatus]"))
                addArg("status_not_in", Variable("status_not_in", "[MediaListStatus]"))
                addArg("status_not", Variable("status_not", "MediaListStatus"))
                addArg("notes_like", Variable("notes_like", "String"))
                addArg("startedAt_greater", Variable("startedAt_greater", "FuzzyDateInt"))
                addArg("startedAt_lesser", Variable("startedAt_lesser", "FuzzyDateInt"))
                addArg("startedAt_like", Variable("startedAt_like", "String"))
                addArg("completedAt_greater", Variable("completedAt_greater", "FuzzyDateInt"))
                addArg("completedAt_lesser", Variable("completedAt_lesser", "FuzzyDateInt"))
                addArg("completedAt_like", Variable("completedAt_like", "String"))
                addArg("sort", Variable("sort", "[MediaListSort]"))
                field("lists") {
                    field("entries") {
                        useFragment(Fragments.mediaListEntry)
                    }
                    field("name")
                    field("isCustomList")
                    field("isSplitCompletedList")
                    field("status")
                }
                field("user") {
                    field("id")
                    field("name")
                    field("mediaListOptions") {
                        field("scoreFormat")
                        field("animeList") {
                            field("customLists")
                        }
                        field("mangaList") {
                            field("customLists")
                        }
                    }
                }
                field("hasNextChunk")
            }
        }
        val variables = Json.encodeToJsonElement(filter).jsonObject
        return send<MediaListCollectionResult>(query, variables = variables).MediaListCollection
    }

    suspend fun setStatus(mediaId: Int, status: MediaListStatus): MediaList? {
        return updateAnime(mediaId, status)
    }
}
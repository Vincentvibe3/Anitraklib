package io.github.vincentvibe3.anilist

import io.github.vincentvibe3.anilist.serialization.AnilistUser
import io.github.vincentvibe3.anilist.serialization.FavoriteVariables
import io.github.vincentvibe3.anilist.serialization.FuzzyDateInput
import io.github.vincentvibe3.anilist.serialization.UpdateAnimeVariables
import io.github.vincentvibe3.anilist.types.*
import io.github.vincentvibe3.gqlclient.dsl.Variable
import io.github.vincentvibe3.gqlclient.dsl.mutation
import io.github.vincentvibe3.gqlclient.dsl.query
import io.github.vincentvibe3.gqlclient.http.DefaultGQLError
import io.github.vincentvibe3.gqlclient.http.GQLClient
import io.github.vincentvibe3.gqlclient.http.HttpHeader
import kotlinx.serialization.json.*

class Anilist {

    var token = ""

    companion object {
        val gqlclient = GQLClient()
        const val apiEndpoint = "https://graphql.anilist.co"
        const val oauthEndpoint = "https://anilist.co/api/v2/oauth/authorize?client_id=13282&response_type=token"
    }

    suspend fun getAnime(id: Long) {
        val query = query {
            field("Media") {
                addArg("id", Variable("animeId", "Int"))
                field("id")
                field("title") {
                    field("english")
                }
                field("episodes")
                field("genres")
                field("status")
                field("format")
                field("averageScore")
                field("popularity")
            }
        }
        val variables = buildJsonObject {
            put("animeId", id)
        }
        val response = gqlclient.sendQuery<JsonObject, DefaultGQLError>(apiEndpoint, query, variables)
        println(response.data.toString())
    }

    suspend fun getCurrentUser(): AnilistUser? {
        val query = query {
            field("Viewer") {
                field("id")
                field("name")
                field("avatar"){
                    field("medium")
                }
                field("mediaListOptions"){
                    field("scoreFormat")
                    field("animeList"){
                        field("customLists")
                    }
                    field("mangaList"){
                        field("customLists")
                    }
                }
            }
        }
        val result = gqlclient.sendQuery<AnilistUser, AnilistGQLError>(
            apiEndpoint, query, headers = listOf(
                HttpHeader("Authorization", "Bearer $token")
            )
        )
        val errors = result.errors
        if (result.data==null&&errors!=null){
            val errorMessages = errors.joinToString {
                "Message: ${it.message}"
            }
            throw AnilistRequestException(errorMessages, errors)
        }
        return result.data
    }

    suspend fun getList(userId: Int): JsonObject? {
        val query = query {
            field("MediaList") {
                addArg("userId", Variable("userId", "Int"))
                field("id")
            }
        }
        val variables = buildJsonObject {
            put("userId", userId)
        }
        val result = gqlclient.sendQuery<JsonObject, AnilistGQLError>(
            apiEndpoint, query, variables, headers = listOf(
                HttpHeader("Authorization", "Bearer $token")
            )
        )
        result.errors?.forEach {
            println(it.message)
            println(it.validation.toString())
        }
        return result.data
    }

    suspend fun updateProgress(mediaId: Int, progress: Int) {
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
        val result = gqlclient.sendMutation<JsonObject, AnilistGQLError>(
            apiEndpoint, mutation, variables, headers = listOf(
                HttpHeader("Authorization", "Bearer $token")
            )
        )
        result.errors?.forEach {
            println(it.message)
            println(it.validation.toString())
        }
        println(result.data.toString())
    }

    suspend fun updateAnime(
        mediaId: Int,
        status: String? = null,
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
        startedAt: FuzzyDateInput? = null,
        completedAt: FuzzyDateInput? = null,
    ) {
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
                addArg("startedAt", Variable("startedAt", "io.github.vincentvibe3.anilist.serialization.FuzzyDateInput"))
                addArg("completedAt", Variable("completedAt", "io.github.vincentvibe3.anilist.serialization.FuzzyDateInput"))
                field("userId")
                field("id")
                field("progress")
                field("mediaId")
            }
        }
         val variables = Json.encodeToJsonElement(UpdateAnimeVariables(
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
        )).jsonObject
//        val variables = buildJsonObject {
//            put("mediaId", mediaId)
//            status?.let{ put("status", it) }
//            score?.let { put("score", it) }
//            scoreRaw?.let { put("scoreRaw", it) }
//            progress?.let { put("progress", it) }
//            progressVolumes?.let { put("progressVolumes", it) }
//            repeat?.let { put("repeat", it) }
//            priority?.let { put("priority", it) }
//            private?.let { put("private", it) }
//            notes?.let { put("notes", it) }
//            hiddenFromStatusLists?.let{ put("hiddenFromStatusLists", it) }
//            customLists?.let { putJsonArray("customLists") {
//                it.forEach { add(it) }
//            } }
//            advancedScores?.let { putJsonArray("advancedScores") {
//                it.forEach { add(it) }
//            } }
//            startedAt?.let { putJsonObject("startedAt"){
//                it.day?.let { day -> put("day", day) }
//                it.month?.let { month -> put("month", month) }
//                it.year?.let { year -> put("year", year) }
//            } }
//            completedAt?.let { putJsonObject("completedAt"){
//                it.day?.let { day -> put("day", day) }
//                it.month?.let { month -> put("month", month) }
//                it.year?.let { year -> put("year", year) }
//            } }
//        }
        val result = gqlclient.sendMutation<JsonObject, AnilistGQLError>(
            apiEndpoint, mutation, variables, headers = listOf(
                HttpHeader("Authorization", "Bearer $token")
            )
        )
    }

    suspend fun favorite(id:Int, entryType: FavoriteType): String {
        val mutation = mutation {
            field("ToggleFavourite"){
                addArg("animeId", Variable("animeId", "Int"))
                addArg("mangaId", Variable("mangaId", "Int"))
                addArg("characterId", Variable("characterId", "Int"))
                addArg("staffId", Variable("staffId", "Int"))
                addArg("studioId", Variable("studioId", "Int"))
                field("anime"){
                    skip(entryType!=FavoriteType.ANIME)
                    field("nodes"){
                        field("id")
                        field("isFavourite")
                    }
                }
                field("manga"){
                    skip(entryType!=FavoriteType.MANGA)
                    field("nodes"){
                        field("id")
                        field("isFavourite")
                    }
                }
                field("characters"){
                    skip(entryType!=FavoriteType.CHARACTER)
                    field("nodes"){
                        field("id")
                        field("isFavourite")
                    }
                }
                field("staff"){
                    skip(entryType!=FavoriteType.STAFF)
                    field("nodes"){
                        field("id")
                        field("isFavourite")
                    }
                }
                field("studios"){
                    skip(entryType!=FavoriteType.STUDIO)
                    field("nodes"){
                        field("id")
                        field("isFavourite")
                    }
                }
            }
        }
        val variablesObject = when (entryType){
            FavoriteType.ANIME -> FavoriteVariables(animeId = id)
            FavoriteType.MANGA -> FavoriteVariables(mangaId = id)
            FavoriteType.CHARACTER -> FavoriteVariables(characterId = id)
            FavoriteType.STAFF -> FavoriteVariables(staffId = id)
            FavoriteType.STUDIO -> FavoriteVariables(studioId = id)
        }
        val variables = Json.encodeToJsonElement(variablesObject).jsonObject
        val result = gqlclient.sendMutation<FavoriteResult, AnilistGQLError>(
            apiEndpoint, mutation, variables, headers = listOf(
                HttpHeader("Authorization", "Bearer $token")
            )
        )
        val error = result.errors
        if (result.data==null&&error!=null){
            val errorMessages = error.joinToString {
                "Message: ${it.message}"
            }
            throw AnilistRequestException(errorMessages, error)
        }
        return result.data.toString()
    }

    //Deletes a show, id is not the media
    suspend fun deleteMedia(id: Int): Boolean {
        val query = mutation {
            field("DeleteMediaListEntry") {
                addArg("id", Variable("id", "Int"))
                field("deleted")
            }
        }
        val variables = buildJsonObject {
            put("id", id)
        }
        val result = gqlclient.sendMutation<JsonObject, AnilistGQLError>(
            apiEndpoint, query, variables, headers = listOf(
                HttpHeader("Authorization", "Bearer $token")
            )
        )
        val error = result.errors
        if (result.data==null&&error!=null){
            val errorMessages = error.joinToString {
                "Message: ${it.message}"
            }
            throw AnilistRequestException(errorMessages, error)
        }
        return result.data?.get("deleted")?.jsonPrimitive?.boolean ?: false
    }

    suspend fun page(page:Int, perPage:Int){

    }

    suspend fun getShow(
        mediaId: Int,
        idMal:Int,
        startDate:Int,
        endDateInput: Int,
        season: MediaSeason,
        seasonYear:Int,
        type: MediaType,
        format: MediaFormat,
        status: MediaStatus,
        episodes:Int,
        duration:Int,
        chapters:Int,
        volumes:Int,
        isAdult:Boolean,
        genre:String,
        tag:String,
        minimumTagRank:Int,
        tagCategory:String,
        onList:Boolean,
        licensedBy:String,
        licensedById: String,
        averageScore: Int,
        popularity: Int,
        source: MediaSource,
        countryOfOrigin:String,
        isLicensed:Boolean,
        search:String,
        id_not:Int,
        id_in:List<Int>,
        id_not_in:List<Int>,
        idMal_in:List<Int>,
        idMal_not_in:List<Int>,
        startDate_greater: Int,
        startDate_lesser: Int,
        startDate_like:Int,
        endDate_greater:Int,
        endDate_lesser:Int,
        endDate_like:String,
        format_in: MediaFormat,
        format_not: MediaFormat,
        format_not_in: MediaFormat,
        status_in: MediaStatus,
        status_not: MediaStatus,
        status_not_in:List<MediaStatus>,
        episodes_greater:Int,
        duration_greater:Int,
        duration_lesser:Int,
        chapters_greater:Int,
        chapters_lesser:Int,
        volumes_greater:Int,
        volumes_lesser:Int,
        genre_in:List<String>,
        genre_not_in:List<String>,
        tag_in:List<String>,
        tag_not_in:List<String>,
        tagCategory_in: List<String>,
        tagCategory_not_in: List<String>,
        licensedById_in:List<Int>,
        averageScore_not: Int,
        averageScore_greater:Int,
        averageScore_lesser: Int,
        popularity_not: Int,
        popularity_greater: Int,
        popularity_lesser:Int,
        source_in:List<MediaSource>,
        sort: MediaSort
    ): Boolean {
        val query = mutation {
            field("DeleteMediaListEntry") {
                addArg("id", Variable("id", "Int"))
                field("deleted")
            }
        }
        val variables = buildJsonObject {
            put("id", mediaId)
        }
        val result = gqlclient.sendMutation<JsonObject, AnilistGQLError>(
            apiEndpoint, query, variables, headers = listOf(
                HttpHeader("Authorization", "Bearer $token")
            )
        )
        val error = result.errors
        if (result.data==null&&error!=null){
            val errorMessages = error.joinToString {
                "Message: ${it.message}"
            }
            throw AnilistRequestException(errorMessages, error)
        }
        return result.data?.get("deleted")?.jsonPrimitive?.boolean ?: false
    }
}
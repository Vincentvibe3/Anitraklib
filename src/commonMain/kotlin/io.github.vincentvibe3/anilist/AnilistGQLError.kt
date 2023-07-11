package io.github.vincentvibe3.anilist

import io.github.vincentvibe3.gqlclient.http.GQLError
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
class AnilistGQLError(
    override val extensions: JsonObject?=null,
    override val locations: List<GQLError.ErrorLocations>?,
    override val message: String,
    override val path: List<String>?=null,
    val validation:JsonObject?=null,
    val status:Int
) :GQLError {
}
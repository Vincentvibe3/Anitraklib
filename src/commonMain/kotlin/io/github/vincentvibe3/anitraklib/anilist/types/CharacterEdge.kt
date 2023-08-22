package io.github.vincentvibe3.anitraklib.anilist.types

import kotlinx.serialization.Serializable

@Serializable
data class CharacterEdge(
    val node: Character,
    val id: Int,
    val role: CharacterRole,
    val name: String?,
    val voiceActors: List<Staff>,
    val voiceActorRoles: List<StaffRoleType>?=null,
    val media: List<Media>?=null,
    val favouriteOrder: Int?=null
)

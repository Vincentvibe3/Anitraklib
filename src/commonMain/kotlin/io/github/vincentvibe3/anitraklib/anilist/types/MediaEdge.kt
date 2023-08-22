package io.github.vincentvibe3.anitraklib.anilist.types

import kotlinx.serialization.Serializable

@Serializable
data class MediaEdge(
    val node: Media,
    val id: Int,
    val relationType: MediaRelation,
    val isMainStudio: Boolean?=null,
    val characters: List<Character>?=null,
    val characterRole: CharacterRole?=null,
    val characterName: String?=null,
    val roleNotes: String?=null,
    val dubGroup: String?=null,
    val staffRole: String?=null,
    val voiceActors: Staff?=null,
    val voiceActorRoles: StaffRoleType?=null,
    val favouriteOrder: Int?=null
)

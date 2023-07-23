package io.github.vincentvibe3.anitraklib.anilist.types

import kotlinx.serialization.Serializable

@Serializable
data class MediaEdge(
    val node: Media,
    val id: Int,
    val relationType: MediaRelation,
    val isMainStudio: Boolean,
    val characters: List<Character>,
    val characterRole: CharacterRole,
    val characterName: String,
    val roleNotes: String,
    val dubGroup: String,
    val staffRole: String,
    val voiceActors: Staff,
    val voiceActorRoles: StaffRoleType,
    val favouriteOrder: Int
)

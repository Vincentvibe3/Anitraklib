package io.github.vincentvibe3.anilist.types

import kotlinx.serialization.Serializable

@Serializable
data class CharacterEdge(
    val node:Character,
    val id:Int,
    val role:CharacterRole,
    val name:String,
    val voiceActors:List<Staff>,
    val voiceActorRoles:List<StaffRoleType>,
    val media:List<Media>,
    val favouriteOrder:Int
)

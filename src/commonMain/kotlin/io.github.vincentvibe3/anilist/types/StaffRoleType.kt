package io.github.vincentvibe3.anilist.types

import kotlinx.serialization.Serializable

@Serializable
data class StaffRoleType(
    val voiceActor:Staff,
    val roleNotes:String,
    val dubGroup:String
)

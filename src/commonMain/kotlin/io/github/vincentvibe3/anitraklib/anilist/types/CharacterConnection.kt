package io.github.vincentvibe3.anitraklib.anilist.types

import kotlinx.serialization.Serializable

@Serializable
data class CharacterConnection(
    val edges: List<CharacterEdge>? = null,
    val nodes: List<Character>,
    val pageInfo: PageInfo
)

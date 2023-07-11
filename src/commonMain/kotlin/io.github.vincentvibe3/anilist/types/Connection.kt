package io.github.vincentvibe3.anilist.types

import kotlinx.serialization.Serializable

@Serializable
data class Connection(
    val nodes:List<Media>
)
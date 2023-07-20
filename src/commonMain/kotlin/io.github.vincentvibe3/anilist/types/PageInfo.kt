package io.github.vincentvibe3.anilist.types

import kotlinx.serialization.Serializable

@Serializable
data class PageInfo(
    val total:Int?,
    val perPage:Int?,
    val currentPage:Int?,
    val lastPage:Int?,
    val hasNextPage:Boolean
)

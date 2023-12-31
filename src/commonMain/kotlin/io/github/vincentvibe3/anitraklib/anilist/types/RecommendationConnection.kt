package io.github.vincentvibe3.anitraklib.anilist.types

import kotlinx.serialization.Serializable

@Serializable
data class RecommendationConnection(
    val edges: List<RecommendationEdge>? = null,
    val nodes: List<Recommendation>,
    val pageInfo: PageInfo
)

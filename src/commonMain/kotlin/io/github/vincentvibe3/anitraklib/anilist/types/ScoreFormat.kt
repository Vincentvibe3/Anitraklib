package io.github.vincentvibe3.anitraklib.anilist.types

import kotlinx.serialization.Serializable

@Serializable
enum class ScoreFormat{
    POINT_100, POINT_10_DECIMAL, POINT_10,
    POINT_5, POINT_3
}
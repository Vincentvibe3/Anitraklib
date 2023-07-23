package io.github.vincentvibe3.anitraklib.anilist.types

import kotlinx.serialization.Serializable

@Serializable
enum class MediaRelation {
    ADAPTATION, PREQUEL, SEQUEL,
    PARENT, SIDE_STORY, CHARACTER,
    SUMMARY, ALTERNATIVE, SPIN_OFF,
    OTHER, SOURCE, COMPILATION, CONTAINS
}

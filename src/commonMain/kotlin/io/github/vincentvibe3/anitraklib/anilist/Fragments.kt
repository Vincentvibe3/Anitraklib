package io.github.vincentvibe3.anitraklib.anilist

import io.github.vincentvibe3.gqlclient.dsl.fragment

object Fragments {

    val mediaListEntry = fragment("mediaListEntryFragment", "MediaList"){
        field("id")
        field("userId")
        field("mediaId")
        field("status")
        field("score")
        field("progress")
        field("progressVolumes")
        field("repeat")
        field("priority")
        field("private")
        field("notes")
        field("hiddenFromStatusLists")
        field("customLists")
        field("advancedScores")
        field("startedAt") {
            field("year")
            field("month")
            field("day")
        }
        field("completedAt") {
            field("year")
            field("month")
            field("day")
        }
        field("notes")
        field("repeat")
        field("updatedAt")
        field("createdAt")
        field("media") {
            field("id")
            field("episodes")
            field("chapters")
            field("title") {
                field("romaji") {
                    addArg("stylised", "true")
                }
                field("english") {
                    addArg("stylised", "true")
                }
                field("native") {
                    addArg("stylised", "true")
                }
                field("userPreferred")
            }
            field("coverImage") {
                field("extraLarge")
                field("large")
                field("medium")
                field("color")
            }
            field("isFavourite")
        }
    }

}
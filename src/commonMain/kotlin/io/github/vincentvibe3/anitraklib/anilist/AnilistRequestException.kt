package io.github.vincentvibe3.anitraklib.anilist

class AnilistRequestException(val errorData: List<AnilistGQLError>) :
    Exception(
        errorData.joinToString(", ") {
            "Message: ${it.message}"
        }
    )
package io.github.vincentvibe3.anitraklib.anilist

class AnilistRequestException(val errorData: List<AnilistGQLError>) :
    Exception(
        errorData.joinToString(", ") {
            val validationData = if (it.message=="validation"){
                " ${it.validation.toString()}"
            } else {
                ""
            }
            "Message: ${it.message}${validationData}"
        }
    )
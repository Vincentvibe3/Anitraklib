package io.github.vincentvibe3.anilist

class AnilistRequestException(override val message:String, val errorData:List<AnilistGQLError>): Exception() {
}
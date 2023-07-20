package io.github.vincentvibe3.anilist.serialization

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProgressUpdateResult(
    @SerialName("SaveMediaListEntry")
    val saveMediaListEntry:ProgressUpdate

){
    @Serializable
    data class ProgressUpdate(
        val userId:Int,
        val id:Int,
        val progress:Int,
        val mediaId:Int
    )
}

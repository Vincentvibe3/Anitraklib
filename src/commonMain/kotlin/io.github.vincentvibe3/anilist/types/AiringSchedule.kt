import io.github.vincentvibe3.anilist.types.Media
import kotlinx.serialization.Serializable

@Serializable
data class AiringSchedule(
    val id:Int,
    val airingAt:Int,
    val timeUntilAiring:Int,
    val episode:Int,
    val mediaId:Int,
    val media: Media?=null
)
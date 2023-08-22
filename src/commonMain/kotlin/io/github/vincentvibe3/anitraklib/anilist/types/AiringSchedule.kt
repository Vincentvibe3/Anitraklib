import io.github.vincentvibe3.anitraklib.anilist.types.Media
import kotlinx.serialization.Serializable

@Serializable
data class AiringSchedule(
    val id: Int,
    val airingAt: Int,
    val timeUntilAiring: Int?=null,
    val episode: Int,
    val mediaId: Int?=null,
    val media: Media? = null
)
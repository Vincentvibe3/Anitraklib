package io.github.vincentvibe3.anilist.serialization

import kotlinx.serialization.Serializable

@Serializable
data class FuzzyDateInput(
    val year:Int?,
    val month:Int?,
    val day:Int?
) {
    init{
        val valid = listOfNotNull(year, month, day).isNotEmpty()
        if (!valid){
            throw IllegalStateException("Year, Month and Day may not all be null")
        }
    }
}
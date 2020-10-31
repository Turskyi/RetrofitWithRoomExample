package ua.turskyi.retrofitwithroomexample.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import ua.turskyi.retrofitwithroomexample.domain.Video

@Entity
data class VideoEntity constructor(
    @PrimaryKey
    val url: String,
    val updated: String,
    val title: String,
    val description: String,
    val thumbnail: String,
   /* in case of migration */
//    val birthday: Long,
)

fun List<VideoEntity>.asDomainModel(): List<Video> {
    return map {
        Video(
            url = it.url,
            title = it.title,
            description = it.description,
            updated = it.updated,
            thumbnail = it.thumbnail
        )
    }
}

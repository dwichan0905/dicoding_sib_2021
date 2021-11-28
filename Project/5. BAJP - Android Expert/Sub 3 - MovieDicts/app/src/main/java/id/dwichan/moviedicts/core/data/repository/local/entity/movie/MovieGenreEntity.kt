package id.dwichan.moviedicts.core.data.repository.local.entity.movie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_genre")
data class MovieGenreEntity(
    @ColumnInfo(name = "name")
    var name: String? = null,

    @PrimaryKey
    @ColumnInfo(name = "genre_id")
    var id: Int? = null
)

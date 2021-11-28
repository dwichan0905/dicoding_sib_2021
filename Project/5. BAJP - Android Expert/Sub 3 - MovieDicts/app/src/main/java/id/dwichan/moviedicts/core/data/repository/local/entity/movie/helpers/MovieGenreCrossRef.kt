package id.dwichan.moviedicts.core.data.repository.local.entity.movie.helpers

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "movie_with_genre", primaryKeys = ["movie_id", "genre_id"])
data class MovieGenreCrossRef(
    @ColumnInfo(name = "movie_id")
    @NonNull
    var movieId: Int,

    @ColumnInfo(name = "genre_id")
    @NonNull
    var genreId: Int
)

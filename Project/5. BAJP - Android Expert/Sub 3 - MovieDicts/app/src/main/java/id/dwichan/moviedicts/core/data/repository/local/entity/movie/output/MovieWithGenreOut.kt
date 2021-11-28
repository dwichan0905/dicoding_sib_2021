package id.dwichan.moviedicts.core.data.repository.local.entity.movie.output

import androidx.room.ColumnInfo

data class MovieWithGenreOut(
    @ColumnInfo(name = "name")
    var name: String? = null,

    @ColumnInfo(name = "genre_id")
    var id: Int? = null
)

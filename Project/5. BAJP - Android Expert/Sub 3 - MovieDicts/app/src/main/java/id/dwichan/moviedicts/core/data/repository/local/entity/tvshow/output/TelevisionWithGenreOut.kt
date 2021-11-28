package id.dwichan.moviedicts.core.data.repository.local.entity.tvshow.output

import androidx.room.ColumnInfo

data class TelevisionWithGenreOut(
    @ColumnInfo(name = "name")
    var name: String? = null,

    @ColumnInfo(name = "genre_id")
    var id: Int? = null
)

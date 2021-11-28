package id.dwichan.moviedicts.core.data.repository.local.entity.tvshow.helpers

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "television_with_genre", primaryKeys = ["tv_id", "genre_id"])
data class TelevisionGenreCrossRef(
    @ColumnInfo(name = "tv_id")
    @NonNull
    var tvId: Int,

    @ColumnInfo(name = "genre_id")
    @NonNull
    var genreId: Int
)
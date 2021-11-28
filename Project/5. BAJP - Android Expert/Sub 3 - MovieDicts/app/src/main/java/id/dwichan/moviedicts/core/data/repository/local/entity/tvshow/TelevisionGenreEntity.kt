package id.dwichan.moviedicts.core.data.repository.local.entity.tvshow

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "television_genre")
data class TelevisionGenreEntity(
    @ColumnInfo(name = "name")
    var name: String? = null,

    @PrimaryKey
    @ColumnInfo(name = "genre_id")
    var id: Int? = null
)

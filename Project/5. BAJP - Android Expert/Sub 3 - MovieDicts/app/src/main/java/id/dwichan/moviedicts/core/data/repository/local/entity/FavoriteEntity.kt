package id.dwichan.moviedicts.core.data.repository.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class FavoriteEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "backdrop_path")
    var backdropPath: String,

    @ColumnInfo(name = "poster_path")
    var posterPath: String,

    @ColumnInfo(name = "media_type")
    var mediaType: String
)

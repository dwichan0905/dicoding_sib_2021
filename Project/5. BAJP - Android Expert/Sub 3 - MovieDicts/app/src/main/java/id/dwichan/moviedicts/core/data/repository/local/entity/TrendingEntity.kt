package id.dwichan.moviedicts.core.data.repository.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trending")
data class TrendingEntity(
    @ColumnInfo(name = "original_title")
    var originalTitle: String? = null,

    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "poster_path")
    var posterPath: String? = null,

    @ColumnInfo(name = "backdrop_path")
    var backdropPath: String? = null,

    @ColumnInfo(name = "vote_average")
    var voteAverage: Double? = null,

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @ColumnInfo(name = "adult")
    var adult: Boolean? = null,

    @ColumnInfo(name = "original_name")
    var originalName: String? = null,

    @ColumnInfo(name = "name")
    var name: String? = null,

    @ColumnInfo(name = "media_type")
    var mediaType: String? = null,

    @ColumnInfo(name = "interval")
    var interval: String? = null,

    @ColumnInfo(name = "timestamp")
    var _timestamp: Long? = null
)
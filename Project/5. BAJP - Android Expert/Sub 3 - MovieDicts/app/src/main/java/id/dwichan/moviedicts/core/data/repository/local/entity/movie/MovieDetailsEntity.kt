package id.dwichan.moviedicts.core.data.repository.local.entity.movie

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_details")
data class MovieDetailsEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movie_id")
    var id: Int,

    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "backdrop_path")
    var backdropPath: String? = null,

    @ColumnInfo(name = "overview")
    var overview: String? = null,

    @ColumnInfo(name = "original_title")
    var originalTitle: String? = null,

    @ColumnInfo(name = "runtime")
    var runtime: Int? = null,

    @ColumnInfo(name = "poster_path")
    var posterPath: String? = null,

    @ColumnInfo(name = "release_date")
    var releaseDate: String? = null,

    @ColumnInfo(name = "vote_average")
    var voteAverage: Double? = null,

    @ColumnInfo(name = "tagline")
    var tagline: String? = null,

    @ColumnInfo(name = "adult")
    var adult: Boolean? = null,

    @ColumnInfo(name = "status")
    var status: String? = null
)
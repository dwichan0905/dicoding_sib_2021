package id.dwichan.moviedicts.core.data.repository.local.entity.tvshow

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "television_details")
data class TelevisionDetailsEntity(

    @PrimaryKey
    @ColumnInfo(name = "tv_id")
    var tvId: Int? = null,

    @ColumnInfo(name = "number_of_episodes")
    var numberOfEpisodes: Int? = null,

    @ColumnInfo(name = "backdrop_path")
    var backdropPath: String? = null,

    @ColumnInfo(name = "number_of_seasons")
    var numberOfSeasons: Int? = null,

    @ColumnInfo(name = "first_air_date")
    var firstAirDate: String? = null,

    @ColumnInfo(name = "overview")
    var overview: String? = null,

    @ColumnInfo(name = "poster_path")
    var posterPath: String? = null,

    @ColumnInfo(name = "original_name")
    var originalName: String? = null,

    @ColumnInfo(name = "vote_average")
    var voteAverage: Double? = null,

    @ColumnInfo(name = "name")
    var name: String? = null,

    @ColumnInfo(name = "tagline")
    var tagline: String? = null,

    @ColumnInfo(name = "episode_run_time")
    var episodeRunTime: Int? = null,

    @ColumnInfo(name = "status")
    var status: String? = null
)

package id.dwichan.moviedicts.core.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TelevisionDetailsDataEntity(
    var numberOfEpisodes: Int? = null,
    var backdropPath: String? = null,
    var genres: List<GenresDataEntity?>? = null,
    var numberOfSeasons: Int? = null,
    var firstAirDate: String? = null,
    var overview: String? = null,
    var createdBy: List<CreatedByDataEntity?>? = null,
    var posterPath: String? = null,
    var productionCompanies: List<ProductionCompaniesDataEntity?>? = null,
    var originalName: String? = null,
    var voteAverage: Double? = null,
    var name: String? = null,
    var tagline: String? = null,
    var episodeRunTime: List<Int?>? = null,
    var status: String? = null
) : Parcelable
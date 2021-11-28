package id.dwichan.moviedicts.core.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDetailsDataEntity(
    var title: String? = null,
    var backdropPath: String? = null,
    var genres: List<GenresDataEntity?>? = null,
    var overview: String? = null,
    var originalTitle: String? = null,
    var runtime: Int? = null,
    var posterPath: String? = null,
    var productionCompanies: List<ProductionCompaniesDataEntity?>? = null,
    var releaseDate: String? = null,
    var voteAverage: Double? = null,
    var tagline: String? = null,
    var adult: Boolean? = null,
    var status: String? = null
) : Parcelable
package id.dwichan.moviedicts.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TrendingEntity(
    var overview: String? = null,
    var originalLanguage: String? = null,
    var originalTitle: String? = null,
    var video: Boolean? = null,
    var title: String? = null,
    var genreIds: List<Int?>? = null,
    var posterPath: String? = null,
    var backdropPath: String? = null,
    var releaseDate: String? = null,
    var voteAverage: Double? = null,
    var popularity: Double? = null,
    var id: Int? = null,
    var adult: Boolean? = null,
    var voteCount: Int? = null,
    var firstAirDate: String? = null,
    var originCountry: List<String?>? = null,
    var originalName: String? = null,
    var name: String? = null
) : Parcelable

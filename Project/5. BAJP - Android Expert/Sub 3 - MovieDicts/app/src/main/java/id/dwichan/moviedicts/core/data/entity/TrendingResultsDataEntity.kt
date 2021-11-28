package id.dwichan.moviedicts.core.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TrendingResultsDataEntity(
    val originalTitle: String? = null,
    val title: String? = null,
    val posterPath: String? = null,
    val backdropPath: String? = null,
    val voteAverage: Double? = null,
    val id: Int? = null,
    val adult: Boolean? = null,
    val originalName: String? = null,
    val name: String? = null
) : Parcelable
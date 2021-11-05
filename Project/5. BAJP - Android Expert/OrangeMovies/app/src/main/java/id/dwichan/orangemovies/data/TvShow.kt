package id.dwichan.orangemovies.data

import androidx.annotation.DrawableRes

data class TvShow (
    var tvShowId: String,
    var title: String,
    var year: Int,
    var synopsis: String,
    var certification: String,
    var category: String,
    var userScore: Int,
    @DrawableRes var poster: Int,
    var crews: ArrayList<Crew>,
    var duration: String
)
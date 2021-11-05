package id.dwichan.orangemovies.data

import androidx.annotation.DrawableRes

data class Movie (
        var movieId: String,
        var title: String,
        var releaseDate: String,
        var synopsis: String,
        var certification: String,
        var category: String,
        var userScore: Int,
        @DrawableRes var poster: Int,
        var crews: ArrayList<Crew>,
        var duration: String,
        var highlight: Boolean
)
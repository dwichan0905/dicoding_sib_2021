package id.dwichan.moviedicts.data.repository

interface MoviesDataSource {

    fun getTrendingMoviesToday()

    fun getTrendingMoviesWeekly()

    fun getMovieDetails(id: Int)
}
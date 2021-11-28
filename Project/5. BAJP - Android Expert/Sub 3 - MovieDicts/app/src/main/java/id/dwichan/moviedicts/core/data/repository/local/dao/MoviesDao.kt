package id.dwichan.moviedicts.core.data.repository.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.dwichan.moviedicts.core.data.repository.local.entity.movie.MovieDetailsEntity
import id.dwichan.moviedicts.core.data.repository.local.entity.movie.MovieGenreEntity
import id.dwichan.moviedicts.core.data.repository.local.entity.movie.MovieProductionCompanyEntity
import id.dwichan.moviedicts.core.data.repository.local.entity.movie.helpers.MovieGenreCrossRef
import id.dwichan.moviedicts.core.data.repository.local.entity.movie.helpers.MovieProductionCompanyCrossRef
import id.dwichan.moviedicts.core.data.repository.local.entity.movie.output.MovieWithGenreOut
import id.dwichan.moviedicts.core.data.repository.local.entity.movie.output.MovieWithProdCompanyOut

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movie_details WHERE movie_id = :id LIMIT 1")
    fun getMovieDetails(id: Int): MovieDetailsEntity

    @Query(
        """
        SELECT a.genre_id, b.name
        FROM movie_with_genre a
        INNER JOIN movie_genre b ON a.genre_id = b.genre_id
        WHERE a.movie_id = :movieId
    """
    )
    fun getMovieGenres(movieId: Int): List<MovieWithGenreOut>

    @Query(
        """
        SELECT a.prod_id, b.name, b.logo_path
        FROM movie_with_production_company a
        INNER JOIN movie_prod_company b ON a.prod_id = b.prod_id
        WHERE a.movie_id = :movieId
    """
    )
    fun getMovieProductionCompanies(movieId: Int): List<MovieWithProdCompanyOut>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieDetails(movieDetails: MovieDetailsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieGenre(movieGenre: MovieGenreEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieCrossGenre(movieGenreCrossRef: MovieGenreCrossRef)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieProductionCompany(productionCompany: MovieProductionCompanyEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieCrossProductionCompany(movieProductionCompanyCrossRef: MovieProductionCompanyCrossRef)
}
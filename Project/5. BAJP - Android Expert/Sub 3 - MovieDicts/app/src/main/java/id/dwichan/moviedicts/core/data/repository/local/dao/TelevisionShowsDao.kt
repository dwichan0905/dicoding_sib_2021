package id.dwichan.moviedicts.core.data.repository.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.dwichan.moviedicts.core.data.repository.local.entity.tvshow.TelevisionCreatedByEntity
import id.dwichan.moviedicts.core.data.repository.local.entity.tvshow.TelevisionDetailsEntity
import id.dwichan.moviedicts.core.data.repository.local.entity.tvshow.TelevisionGenreEntity
import id.dwichan.moviedicts.core.data.repository.local.entity.tvshow.TelevisionProductionCompanyEntity
import id.dwichan.moviedicts.core.data.repository.local.entity.tvshow.helpers.TelevisionCreatorCrossRef
import id.dwichan.moviedicts.core.data.repository.local.entity.tvshow.helpers.TelevisionGenreCrossRef
import id.dwichan.moviedicts.core.data.repository.local.entity.tvshow.helpers.TelevisionProductionCompanyCrossRef
import id.dwichan.moviedicts.core.data.repository.local.entity.tvshow.output.TelevisionWithCreatedByOut
import id.dwichan.moviedicts.core.data.repository.local.entity.tvshow.output.TelevisionWithGenreOut
import id.dwichan.moviedicts.core.data.repository.local.entity.tvshow.output.TelevisionWithProductionCompanyOut

@Dao
interface TelevisionShowsDao {
    @Query("SELECT * FROM television_details WHERE tv_id = :id LIMIT 1")
    fun getTvShowDetails(id: Int): TelevisionDetailsEntity

    @Query(
        """
        SELECT a.genre_id, b.name
        FROM television_with_genre a
        INNER JOIN television_genre b ON a.genre_id = b.genre_id
        WHERE a.tv_id = :id
    """
    )
    fun getTvShowGenres(id: Int): List<TelevisionWithGenreOut>

    @Query(
        """
        SELECT a.prod_id, b.name, b.logo_path
        FROM television_with_prod_company a
        INNER JOIN television_prod_company b ON a.prod_id = b.prod_id
        WHERE a.tv_id = :id
    """
    )
    fun getTvShowProductionCompanies(id: Int): List<TelevisionWithProductionCompanyOut>

    @Query(
        """
        SELECT a.creator_id, b.name, b.credit_id, b.gender, b.profile_path
        FROM television_with_creator a
        INNER JOIN television_created_by b ON a.creator_id = b.creator_id
        WHERE a.tv_id = :id
    """
    )
    fun getTvShowCreators(id: Int): List<TelevisionWithCreatedByOut>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShowDetails(televisionDetails: TelevisionDetailsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShowProductionCompany(tvProductionCompany: TelevisionProductionCompanyEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShowCrossProdCompany(televisionProductionCompanyCrossRef: TelevisionProductionCompanyCrossRef)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShowGenre(televisionGenre: TelevisionGenreEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShowCrossGenre(televisionGenreCrossRef: TelevisionGenreCrossRef)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShowCreator(televisionCreators: TelevisionCreatedByEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShowCrossCreator(televisionCreatorCrossRef: TelevisionCreatorCrossRef)
}
package id.dwichan.moviedicts.core.data.repository.local.dao

import androidx.room.*
import id.dwichan.moviedicts.core.data.repository.local.entity.FavoriteEntity

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorite WHERE id = :id")
    fun getFavoriteMovie(id: Int): List<FavoriteEntity>

    @Query("SELECT * FROM favorite")
    fun getAllFavoriteMovie(): List<FavoriteEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFavoriteMovie(favorite: FavoriteEntity)

    @Delete
    fun deleteFavorite(favorite: FavoriteEntity)
}
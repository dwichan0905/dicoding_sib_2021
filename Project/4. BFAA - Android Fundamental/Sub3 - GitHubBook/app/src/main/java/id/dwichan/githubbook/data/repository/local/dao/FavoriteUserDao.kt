package id.dwichan.githubbook.data.repository.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import id.dwichan.githubbook.data.repository.local.entity.FavoriteUser

@Dao
interface FavoriteUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: FavoriteUser)

    @Delete
    fun delete(user: FavoriteUser)

    @Query("SELECT * FROM favorite_user ORDER BY dateAdded DESC")
    fun getAllUsers(): LiveData<List<FavoriteUser>>

    @Query("SELECT * FROM favorite_user WHERE login LIKE '%' || :query || '%'")
    fun searchUsers(query: String): LiveData<List<FavoriteUser>>

    @Query("SELECT * FROM favorite_user WHERE login = :username")
    fun getUser(username: String): LiveData<List<FavoriteUser>>
}
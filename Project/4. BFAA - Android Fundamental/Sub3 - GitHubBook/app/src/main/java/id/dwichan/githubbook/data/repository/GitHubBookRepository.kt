package id.dwichan.githubbook.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import id.dwichan.githubbook.data.repository.local.GitHubBookRoomDatabase
import id.dwichan.githubbook.data.repository.local.dao.FavoriteUserDao
import id.dwichan.githubbook.data.repository.local.entity.FavoriteUser
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class GitHubBookRepository(application: Application) {

    private val mFavoriteUserDao: FavoriteUserDao
    private val executors: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = GitHubBookRoomDatabase.getDatabase(application)
        mFavoriteUserDao = db.favoriteUserDao()
    }

    fun getAllUsers(): LiveData<List<FavoriteUser>> = mFavoriteUserDao.getAllUsers()

    fun getUser(username: String): LiveData<List<FavoriteUser>> = mFavoriteUserDao.getUser(username)

    fun searchUsers(query: String): LiveData<List<FavoriteUser>> =
        mFavoriteUserDao.searchUsers(query)

    fun insertNewUser(user: FavoriteUser) {
        executors.execute {
            mFavoriteUserDao.insert(user)
        }
    }

    fun deleteUser(user: FavoriteUser) {
        executors.execute {
            mFavoriteUserDao.delete(user)
        }
    }

}
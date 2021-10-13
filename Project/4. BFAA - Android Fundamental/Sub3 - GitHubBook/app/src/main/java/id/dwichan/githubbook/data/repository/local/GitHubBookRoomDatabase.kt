package id.dwichan.githubbook.data.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.dwichan.githubbook.data.repository.local.dao.FavoriteUserDao
import id.dwichan.githubbook.data.repository.local.entity.FavoriteUser

@Database(entities = [FavoriteUser::class], version = 1, exportSchema = false)
abstract class GitHubBookRoomDatabase : RoomDatabase() {

    abstract fun favoriteUserDao(): FavoriteUserDao

    companion object {
        private const val DATABASE_NAME = "GitHubBookDatabase"

        @Volatile
        private var instance: GitHubBookRoomDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): GitHubBookRoomDatabase =
            instance ?: synchronized(this) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    GitHubBookRoomDatabase::class.java,
                    DATABASE_NAME
                ).build()
                instance as GitHubBookRoomDatabase
            }
    }
}
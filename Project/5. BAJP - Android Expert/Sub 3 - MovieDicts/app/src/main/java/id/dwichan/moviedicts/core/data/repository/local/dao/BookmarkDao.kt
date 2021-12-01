package id.dwichan.moviedicts.core.data.repository.local.dao

import androidx.paging.DataSource
import androidx.room.*
import id.dwichan.moviedicts.core.data.repository.local.entity.BookmarkEntity

@Dao
interface BookmarkDao {
    @Query("SELECT * FROM bookmark WHERE id = :id")
    fun getBookmark(id: Int): List<BookmarkEntity>

    @Query("SELECT * FROM bookmark WHERE media_type = :mediaType")
    fun getAllBookmark(mediaType: String): DataSource.Factory<Int, BookmarkEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertBookmarkMovie(bookmark: BookmarkEntity)

    @Delete
    fun deleteBookmark(bookmark: BookmarkEntity)
}
package id.dwichan.moviedicts.core.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import id.dwichan.moviedicts.core.data.repository.local.LocalDataSource
import id.dwichan.moviedicts.core.util.DataDummy
import id.dwichan.moviedicts.vo.Type
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito

class BookmarkRepositoryTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val localDataSource = Mockito.mock(LocalDataSource::class.java)
    private val repository = FakeBookmarkRepository(
        localDataSource
    )

    @Test
    fun `Bookmark Movies should be returned correct values`() {
        val bookmarks = DataDummy.generateDummyBookmarkMovies()
        Mockito.`when`(localDataSource.getAllBookmark(Type.MEDIA_TYPE_MOVIES)).thenReturn(bookmarks)
        val response = repository.getAllBookmark(Type.MEDIA_TYPE_MOVIES)
        Mockito.verify(localDataSource).getAllBookmark(Type.MEDIA_TYPE_MOVIES)
        assertNotNull(response)
        assertNotEquals(0, response.value?.size)
    }

    @Test
    fun `Bookmark Television Show should be returned correct values`() {
        val bookmarks = DataDummy.generateDummyBookmarkTvShow()
        Mockito.`when`(localDataSource.getAllBookmark(Type.MEDIA_TYPE_TELEVISION)).thenReturn(bookmarks)
        val response = repository.getAllBookmark(Type.MEDIA_TYPE_TELEVISION)
        Mockito.verify(localDataSource).getAllBookmark(Type.MEDIA_TYPE_TELEVISION)
        assertNotNull(response)
        assertNotEquals(0, response.value?.size)
    }
}
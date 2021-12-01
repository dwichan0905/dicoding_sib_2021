package id.dwichan.moviedicts.core.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import id.dwichan.moviedicts.core.data.repository.local.LocalDataSource
import id.dwichan.moviedicts.core.util.DataDummy
import id.dwichan.moviedicts.core.util.ListDataSource
import id.dwichan.moviedicts.core.util.PagedListUtil
import id.dwichan.moviedicts.vo.Resource
import id.dwichan.moviedicts.vo.Type
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
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

    @Suppress("UNCHECKED_CAST")
    @Test
    fun `Bookmark Movies should be returned correct values`() {
        val bookmarks = ListDataSource(DataDummy.generateDummyBookmarkMovies())
        Mockito.`when`(localDataSource.getAllBookmark(Type.MEDIA_TYPE_MOVIES)).thenReturn(bookmarks)
        repository.getAllBookmark(Type.MEDIA_TYPE_MOVIES)

        val response =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyBookmarkMovies()))
        Mockito.verify(localDataSource).getAllBookmark(Type.MEDIA_TYPE_MOVIES)
        assertNotNull(response)
        assertNotEquals(0, response.data?.size)
    }

    @Suppress("UNCHECKED_CAST")
    @Test
    fun `Bookmark Television Show should be returned correct values`() {
        val bookmarks = ListDataSource(DataDummy.generateDummyBookmarkTvShow())
        Mockito.`when`(localDataSource.getAllBookmark(Type.MEDIA_TYPE_TELEVISION))
            .thenReturn(bookmarks)
        repository.getAllBookmark(Type.MEDIA_TYPE_TELEVISION)

        val response =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyBookmarkTvShow()))
        Mockito.verify(localDataSource).getAllBookmark(Type.MEDIA_TYPE_TELEVISION)
        assertNotNull(response)
        assertNotEquals(0, response.data?.size)
    }
}
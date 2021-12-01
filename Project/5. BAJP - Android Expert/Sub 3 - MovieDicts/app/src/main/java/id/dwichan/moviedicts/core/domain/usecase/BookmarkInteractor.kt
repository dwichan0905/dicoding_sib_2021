package id.dwichan.moviedicts.core.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import id.dwichan.moviedicts.core.data.entity.MovieTelevisionDataEntity
import id.dwichan.moviedicts.core.domain.repository.BookmarkDataSource
import javax.inject.Inject

class BookmarkInteractor @Inject constructor(private val bookmarkDataSource: BookmarkDataSource) :
    BookmarkUseCase {
    override fun getAllBookmark(mediaType: String): LiveData<PagedList<MovieTelevisionDataEntity>> =
        bookmarkDataSource.getAllBookmark(mediaType)
}
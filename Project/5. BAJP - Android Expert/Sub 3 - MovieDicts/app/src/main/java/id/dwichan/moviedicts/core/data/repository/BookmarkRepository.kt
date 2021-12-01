package id.dwichan.moviedicts.core.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import id.dwichan.moviedicts.core.data.entity.MovieTelevisionDataEntity
import id.dwichan.moviedicts.core.data.repository.local.LocalDataSource
import id.dwichan.moviedicts.core.domain.repository.BookmarkDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookmarkRepository @Inject constructor(
    private val localDataSource: LocalDataSource
) : BookmarkDataSource {

    override fun getAllBookmark(mediaType: String): LiveData<PagedList<MovieTelevisionDataEntity>> {
        val pagingConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(5)
            .setPageSize(5)
            .build()
        val pagedDb = localDataSource.getAllBookmark(mediaType).map { db ->
            MovieTelevisionDataEntity(
                id = db.id,
                backdropPath = db.backdropPath,
                posterPath = db.posterPath,
                title = db.title,
                mediaType = db.mediaType
            )
        }

        return LivePagedListBuilder(pagedDb, pagingConfig).build()
    }

}
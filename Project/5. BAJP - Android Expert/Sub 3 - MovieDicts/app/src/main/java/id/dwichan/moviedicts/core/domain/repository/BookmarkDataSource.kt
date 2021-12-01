package id.dwichan.moviedicts.core.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import id.dwichan.moviedicts.core.data.entity.MovieTelevisionDataEntity

interface BookmarkDataSource {
    fun getAllBookmark(mediaType: String): LiveData<PagedList<MovieTelevisionDataEntity>>
}
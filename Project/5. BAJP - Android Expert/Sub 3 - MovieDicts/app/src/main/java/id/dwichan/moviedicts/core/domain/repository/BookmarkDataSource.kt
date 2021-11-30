package id.dwichan.moviedicts.core.domain.repository

import androidx.lifecycle.LiveData
import id.dwichan.moviedicts.core.data.entity.MovieTelevisionDataEntity

interface BookmarkDataSource {
    fun getAllBookmark(mediaType: String): LiveData<List<MovieTelevisionDataEntity>>
}
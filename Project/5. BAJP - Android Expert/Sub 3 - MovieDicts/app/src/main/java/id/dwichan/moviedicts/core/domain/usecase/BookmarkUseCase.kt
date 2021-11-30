package id.dwichan.moviedicts.core.domain.usecase

import androidx.lifecycle.LiveData
import id.dwichan.moviedicts.core.data.entity.MovieTelevisionDataEntity

interface BookmarkUseCase {
    fun getAllBookmark(mediaType: String): LiveData<List<MovieTelevisionDataEntity>>
}
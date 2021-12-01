package id.dwichan.moviedicts.ui.main.bookmark.television

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import dagger.hilt.android.lifecycle.HiltViewModel
import id.dwichan.moviedicts.core.data.entity.MovieTelevisionDataEntity
import id.dwichan.moviedicts.core.domain.usecase.BookmarkUseCase
import id.dwichan.moviedicts.vo.Type
import javax.inject.Inject

@HiltViewModel
class BookmarkTelevisionViewModel @Inject constructor(
    bookmarkUseCase: BookmarkUseCase
) : ViewModel() {

    private val reloadTrigger = MutableLiveData<Boolean>()

    val bookmarkList: LiveData<PagedList<MovieTelevisionDataEntity>> = Transformations.switchMap(reloadTrigger) {
        bookmarkUseCase.getAllBookmark(Type.MEDIA_TYPE_TELEVISION)
    }

    fun reload() {
        reloadTrigger.value = true
    }
}
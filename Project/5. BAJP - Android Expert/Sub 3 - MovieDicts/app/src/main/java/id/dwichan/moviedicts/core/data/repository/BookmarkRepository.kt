package id.dwichan.moviedicts.core.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.dwichan.moviedicts.core.data.entity.MovieTelevisionDataEntity
import id.dwichan.moviedicts.core.data.repository.local.LocalDataSource
import id.dwichan.moviedicts.core.domain.repository.BookmarkDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookmarkRepository @Inject constructor(
    private val localDataSource: LocalDataSource
) : BookmarkDataSource {

    override fun getAllBookmark(mediaType: String): LiveData<List<MovieTelevisionDataEntity>> {
        val listFavorite = ArrayList<MovieTelevisionDataEntity>()
        val db = localDataSource.getAllBookmark(mediaType)
        for (position in db.indices) {
            val item = MovieTelevisionDataEntity(
                id = db[position].id,
                backdropPath = db[position].backdropPath,
                posterPath = db[position].posterPath,
                title = db[position].title,
                mediaType = db[position].mediaType
            )
            listFavorite.add(item)
        }

        return MutableLiveData(listFavorite)
    }

}
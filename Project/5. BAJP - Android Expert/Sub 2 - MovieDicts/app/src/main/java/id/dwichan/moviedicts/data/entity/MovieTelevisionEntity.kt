package id.dwichan.moviedicts.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieTelevisionEntity(
    val id: Int,
    val title: String,
    val backdropPath: String,
    val posterPath: String,
    val mediaType: String
) : Parcelable

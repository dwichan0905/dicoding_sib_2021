package id.dwichan.githubbook.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var follower: Int,
    var following: Int,
    var name: String,
    var company: String,
    var location: String,
    var avatar: String,
    var repository: Int,
    var username: String,
    var isFavorite: Boolean = false
) : Parcelable

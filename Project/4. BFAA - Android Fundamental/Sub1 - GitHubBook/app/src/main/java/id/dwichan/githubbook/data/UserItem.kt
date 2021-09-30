package id.dwichan.githubbook.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserItem(
    var follower: Int? = null,
    var following: Int? = null,
    var name: String? = null,
    var company: String? = null,
    var location: String? = null,
    var avatar: String? = null,
    var repository: Int? = null,
    var username: String? = null
) : Parcelable

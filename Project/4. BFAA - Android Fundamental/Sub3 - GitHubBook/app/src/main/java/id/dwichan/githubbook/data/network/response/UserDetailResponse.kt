package id.dwichan.githubbook.data.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserDetailResponse(

    @field:SerializedName("login")
    val login: String? = null,

    @field:SerializedName("company")
    val company: String? = null,

    @field:SerializedName("public_repos")
    val publicRepos: Int? = null,

    @field:SerializedName("followers")
    val followers: Int? = null,

    @field:SerializedName("avatar_url")
    val avatarUrl: String? = null,

    @field:SerializedName("html_url")
    val htmlUrl: String? = null,

    @field:SerializedName("following")
    val following: Int? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("location")
    val location: String? = null

) : Parcelable

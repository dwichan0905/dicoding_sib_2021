package id.dwichan.githubbook.data.repository.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserItem(

    @SerializedName("login")
    val login: String,

    @SerializedName("type")
    val type: String,

    @SerializedName("avatar_url")
    val avatarUrl: String,

    ) : Parcelable
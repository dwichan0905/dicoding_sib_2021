package id.dwichan.githubbook.data.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class UserSearchResponse(

    @SerializedName("total_count")
    val totalCount: Int,

    @SerializedName("items")
    val items: List<UserItem>

) : Parcelable


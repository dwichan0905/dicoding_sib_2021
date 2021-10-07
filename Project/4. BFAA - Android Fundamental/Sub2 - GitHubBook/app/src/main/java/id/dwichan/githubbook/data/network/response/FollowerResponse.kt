package id.dwichan.githubbook.data.network.response

import com.google.gson.annotations.SerializedName

data class FollowerResponse(

	@SerializedName("FollowerResponse")
	val followerResponse: List<UserItem>

)

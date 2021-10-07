package id.dwichan.githubbook.data.network.response

import com.google.gson.annotations.SerializedName

data class FollowingResponse(

	@SerializedName("FollowingResponse")
	val followingResponse: List<UserItem>

)
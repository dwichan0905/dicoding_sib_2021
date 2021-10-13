package id.dwichan.githubbook.data.repository.network.response

import com.google.gson.annotations.SerializedName

data class RepositoryItem(

    @field:SerializedName("full_name")
    val fullName: String? = null,

    @field:SerializedName("html_url")
    val htmlUrl: String? = null,

    @field:SerializedName("name")
    val name: String? = null

)
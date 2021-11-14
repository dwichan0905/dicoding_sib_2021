package id.dwichan.moviedicts.data.repository.remote.response.trending

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

// disini mungkin masih banyak yang unused, untuk disiapkan ke dalam Paging
@Parcelize
data class TrendingResponse(

    @field:SerializedName("page")
    val page: Int? = null,

    @field:SerializedName("total_pages")
    val totalPages: Int? = null,

    @field:SerializedName("results")
    val results: List<TrendingResultsItem?>? = null,

    @field:SerializedName("total_results")
    val totalResults: Int? = null
) : Parcelable

@Parcelize
data class TrendingResultsItem(

    @field:SerializedName("original_title")
    val originalTitle: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @field:SerializedName("backdrop_path")
    val backdropPath: String? = null,

    @field:SerializedName("vote_average")
    val voteAverage: Double? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("adult")
    val adult: Boolean? = null,

    @field:SerializedName("original_name")
    val originalName: String? = null,

    @field:SerializedName("name")
    val name: String? = null
) : Parcelable

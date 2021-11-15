package id.dwichan.moviedicts.core.data.repository.remote.response.television

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TelevisionDetailsResponse(

    @field:SerializedName("number_of_episodes")
    val numberOfEpisodes: Int? = null,

    @field:SerializedName("backdrop_path")
    val backdropPath: String? = null,

    @field:SerializedName("genres")
    val genres: List<TelevisionShowGenresItem?>? = null,

    @field:SerializedName("number_of_seasons")
    val numberOfSeasons: Int? = null,

    @field:SerializedName("first_air_date")
    val firstAirDate: String? = null,

    @field:SerializedName("overview")
    val overview: String? = null,

    @field:SerializedName("created_by")
    val createdBy: List<CreatedByItem?>? = null,

    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @field:SerializedName("production_companies")
    val productionCompanies: List<ProductionCompaniesItem?>? = null,

    @field:SerializedName("original_name")
    val originalName: String? = null,

    @field:SerializedName("vote_average")
    val voteAverage: Double? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("tagline")
    val tagline: String? = null,

    @field:SerializedName("episode_run_time")
    val episodeRunTime: List<Int?>? = null,

    @field:SerializedName("status")
    val status: String? = null
) : Parcelable

@Parcelize
data class ProductionCompaniesItem(

    @field:SerializedName("logo_path")
    val logoPath: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null
) : Parcelable

@Parcelize
data class TelevisionShowGenresItem(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null
) : Parcelable

@Parcelize
data class CreatedByItem(

    @field:SerializedName("gender")
    val gender: Int? = null,

    @field:SerializedName("credit_id")
    val creditId: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("profile_path")
    val profilePath: String? = null,

    @field:SerializedName("id")
    val id: Int? = null
) : Parcelable
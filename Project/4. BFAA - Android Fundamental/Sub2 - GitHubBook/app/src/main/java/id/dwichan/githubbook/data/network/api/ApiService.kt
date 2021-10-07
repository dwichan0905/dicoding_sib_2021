package id.dwichan.githubbook.data.network.api

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import id.dwichan.githubbook.BuildConfig
import id.dwichan.githubbook.data.network.response.FollowerResponse
import id.dwichan.githubbook.data.network.response.FollowingResponse
import id.dwichan.githubbook.data.network.response.UserDetailResponse
import id.dwichan.githubbook.data.network.response.UserSearchResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    companion object {
        fun getApiService(context: Context): ApiService {
            val client = OkHttpClient.Builder()
                .addInterceptor(
                    ChuckerInterceptor.Builder(context)
                        .collector(ChuckerCollector(context))
                        .maxContentLength(250000L)
                        .redactHeaders(emptySet())
                        .alwaysReadResponseBody(false)
                        .build()
                )
                .build()
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.github.com/")
                .client(client)
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }

    @GET("search/users")
    @Headers("Authorization: token ${BuildConfig.GITHUB_SECRET_API}")
    fun searchUser(
        @Query("q") query: String
    ): Call<UserSearchResponse>

    @GET("users/{username}")
    @Headers("Authorization: token ${BuildConfig.GITHUB_SECRET_API}")
    fun getUserDetails(
        @Path("username") username: String
    ): Call<UserDetailResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token ${BuildConfig.GITHUB_SECRET_API}")
    fun getFollowers(
        @Path("username") username: String
    ): Call<FollowerResponse>

    @GET("users/{username}/following")
    @Headers("Authorization: token ${BuildConfig.GITHUB_SECRET_API}")
    fun getFollowing(
        @Path("username") username: String
    ): Call<FollowingResponse>
}
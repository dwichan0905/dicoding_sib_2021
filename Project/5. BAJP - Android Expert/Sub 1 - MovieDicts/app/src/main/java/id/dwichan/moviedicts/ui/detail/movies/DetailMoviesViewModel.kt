package id.dwichan.moviedicts.ui.detail.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.dwichan.moviedicts.data.repository.remote.api.ApiService
import id.dwichan.moviedicts.data.repository.remote.response.movie.MovieDetailsResponse
import id.dwichan.moviedicts.util.SingleEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailMoviesViewModel: ViewModel() {

    private var _data = MutableLiveData<MovieDetailsResponse>()
    val data: LiveData<MovieDetailsResponse> = _data

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var _errorReason = MutableLiveData<SingleEvent<String>>()
    val errorReason: LiveData<SingleEvent<String>> = _errorReason

    private var _movieId = MutableLiveData<Int>()

    init {
        _errorReason.value = SingleEvent("")
    }

    fun setMovieId(id: Int) {
        _movieId.value = id
    }

    fun fetchMovieDetails() {
        _isLoading.value = true
        _errorReason.value = SingleEvent("")

        val api = ApiService.getApiService().getMovieDetails(_movieId.value ?: 0)
        api.enqueue(object : Callback<MovieDetailsResponse> {
            override fun onResponse(
                call: Call<MovieDetailsResponse>,
                response: Response<MovieDetailsResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _data.postValue(responseBody)
                    } else {
                        _errorReason.value = SingleEvent(
                            "Server didn't returned the correct respond [${response.code()}]"
                        )
                        _data.postValue(MovieDetailsResponse())
                    }
                } else {
                    _errorReason.value = SingleEvent(
                        "Server didn't returned the correct respond [${response.code()}]"
                    )
                    _data.postValue(MovieDetailsResponse())
                }
            }

            override fun onFailure(call: Call<MovieDetailsResponse>, t: Throwable) {
                _isLoading.value = false
                _errorReason.value = SingleEvent(t.message as String)
                _data.postValue(MovieDetailsResponse())
                t.printStackTrace()
            }

        })
    }
}
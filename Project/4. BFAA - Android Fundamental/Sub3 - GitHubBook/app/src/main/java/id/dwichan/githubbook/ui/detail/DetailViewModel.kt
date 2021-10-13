package id.dwichan.githubbook.ui.detail

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.dwichan.githubbook.data.repository.GitHubBookRepository
import id.dwichan.githubbook.data.repository.local.entity.FavoriteUser
import id.dwichan.githubbook.data.repository.network.api.ApiService
import id.dwichan.githubbook.data.repository.network.response.UserDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(app: Application) : AndroidViewModel(app) {

    private val repository = GitHubBookRepository(app)

    private var _data = MutableLiveData<UserDetailResponse>()
    val data: LiveData<UserDetailResponse> = _data

    private val _isDataUpdated = MutableLiveData<Boolean>()

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var _isFailed = MutableLiveData<Boolean>()
    val isFailed: LiveData<Boolean> = _isFailed

    init {
        _isDataUpdated.value = false
        _isLoading.value = false
    }

    fun getUserInDatabase(username: String) = repository.getUser(username)

    fun addFavorite(user: FavoriteUser) {
        repository.insertNewUser(user)
    }

    fun deleteFromFavorite(user: FavoriteUser) {
        repository.deleteUser(user)
    }

    fun fetchUserData(username: String) {
        if (_isDataUpdated.value == false) {
            _isLoading.value = true
            val client = ApiService.getApiService(getApplication()).getUserDetails(username)
            client.enqueue(object : Callback<UserDetailResponse> {
                @SuppressLint("NullSafeMutableLiveData")
                override fun onResponse(
                    call: Call<UserDetailResponse>,
                    response: Response<UserDetailResponse>
                ) {
                    if (response.isSuccessful) {
                        _isLoading.value = false
                        val responseBody = response.body()
                        if (responseBody != null) {
                            _data.postValue(responseBody)
                            _isDataUpdated.value = true
                        } else {
                            _isFailed.value = true
                            _isDataUpdated.value = true
                        }
                    } else {
                        _isFailed.value = true
                        _isDataUpdated.value = true
                    }
                }

                override fun onFailure(call: Call<UserDetailResponse>, t: Throwable) {
                    _isLoading.value = false
                    _isFailed.value = true
                    _isDataUpdated.value = true
                }
            })
        }
    }

}
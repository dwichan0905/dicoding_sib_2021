package id.dwichan.githubbook.ui.detail.content.follower

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.dwichan.githubbook.data.network.api.ApiService
import id.dwichan.githubbook.data.network.response.UserItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowerViewModel(app: Application) : AndroidViewModel(app) {

    private var _data = MutableLiveData<List<UserItem>>()
    val data: LiveData<List<UserItem>> = _data

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var _isDataUpdated = MutableLiveData<Boolean>()

    private var _isFailed = MutableLiveData<Boolean>()
    val isFailed: LiveData<Boolean> = _isFailed

    init {
        _isDataUpdated.value = false
    }

    fun fetchFollower(username: String) {
        if (_isDataUpdated.value == false) {
            _isLoading.value = true
            val client = ApiService.getApiService(getApplication()).getFollowers(username)
            client.enqueue(object : Callback<List<UserItem>> {
                @SuppressLint("NullSafeMutableLiveData")
                override fun onResponse(
                    call: Call<List<UserItem>>,
                    response: Response<List<UserItem>>
                ) {
                    _isLoading.value = false
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            _data.postValue(responseBody)
                            _isFailed.value = false
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

                override fun onFailure(call: Call<List<UserItem>>, t: Throwable) {
                    _isLoading.value = false
                    _isFailed.value = true
                    _isDataUpdated.value = true
                }
            })
        }
    }

    fun needUpdateData() {
        _isDataUpdated.value = false
        _isFailed.value = false
    }
}
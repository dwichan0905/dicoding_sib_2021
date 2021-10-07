package id.dwichan.githubbook.ui.detail.content

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.dwichan.githubbook.data.network.api.ApiService
import id.dwichan.githubbook.data.network.response.UserDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(app: Application) : AndroidViewModel(app) {

    private var _data = MutableLiveData<UserDetailResponse>()
    val data: LiveData<UserDetailResponse> = _data

    private val isDataUpdated = MutableLiveData<Boolean>()

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var _isFailed = MutableLiveData<Boolean>()
    val isFailed: LiveData<Boolean> = _isFailed

    init {
        isDataUpdated.value = false
    }

    fun fetchUserData(username: String) {
        if (isDataUpdated.value == false) {
            _isLoading.value = true
            val client = ApiService.getApiService().getUserDetails(username)
            client.enqueue(object : Callback<UserDetailResponse> {
                override fun onResponse(
                    call: Call<UserDetailResponse>,
                    response: Response<UserDetailResponse>
                ) {
                    if (response.isSuccessful) {
                        _isLoading.value = false
                        val responseBody = response.body()
                        if (responseBody != null) {
                            _data.postValue(responseBody)
                            isDataUpdated.value = true
                        } else {
                            _isFailed.value = true
                        }
                    } else {
                        _isFailed.value = true
                    }
                }

                override fun onFailure(call: Call<UserDetailResponse>, t: Throwable) {
                    _isLoading.value = false
                    _isFailed.value = true
                }
            })
        }
    }

    fun needUpdateData() {
        isDataUpdated.value = false
    }

}
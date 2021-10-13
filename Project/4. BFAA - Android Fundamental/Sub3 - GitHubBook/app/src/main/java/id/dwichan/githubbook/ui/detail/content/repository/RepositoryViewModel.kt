package id.dwichan.githubbook.ui.detail.content.repository

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.dwichan.githubbook.data.repository.network.api.ApiService
import id.dwichan.githubbook.data.repository.network.response.RepositoryItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryViewModel(app: Application) : AndroidViewModel(app) {

    private var _data = MutableLiveData<List<RepositoryItem>>()
    val data: LiveData<List<RepositoryItem>> = _data

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var _isDataUpdated = MutableLiveData<Boolean>()

    private var _isFailed = MutableLiveData<Boolean>()
    val isFailed: LiveData<Boolean> = _isFailed

    init {
        _isDataUpdated.value = false
    }

    fun fetchRepositories(username: String) {
        if (_isDataUpdated.value == false) {
            _isLoading.value = true
            val client = ApiService.getApiService(getApplication()).getRepositories(username)
            client.enqueue(object : Callback<List<RepositoryItem>> {
                @SuppressLint("NullSafeMutableLiveData")
                override fun onResponse(
                    call: Call<List<RepositoryItem>>,
                    response: Response<List<RepositoryItem>>
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

                override fun onFailure(call: Call<List<RepositoryItem>>, t: Throwable) {
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
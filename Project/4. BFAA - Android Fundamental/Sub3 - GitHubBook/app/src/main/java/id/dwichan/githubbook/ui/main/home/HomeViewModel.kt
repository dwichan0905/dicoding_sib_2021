package id.dwichan.githubbook.ui.main.home

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.dwichan.githubbook.R
import id.dwichan.githubbook.data.repository.network.api.ApiService
import id.dwichan.githubbook.data.repository.network.response.UserItem
import id.dwichan.githubbook.data.repository.network.response.UserSearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.NumberFormat

class HomeViewModel(app: Application) : AndroidViewModel(app) {

    private var _data = MutableLiveData<List<UserItem>>()
    val data: LiveData<List<UserItem>> = _data

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var _isNotFound = MutableLiveData<Boolean>()
    val isNotFound: LiveData<Boolean> = _isNotFound

    private var _isListVisible = MutableLiveData<Boolean>()
    val isListVisible: LiveData<Boolean> = _isListVisible

    private var _isOnBoarding = MutableLiveData<Boolean>()
    val isOnBoarding: LiveData<Boolean> = _isOnBoarding

    private var _resultMessage = MutableLiveData<String>()
    val resultMessage: LiveData<String> = _resultMessage

    init {
        _isLoading.value = false
    }

    fun requestFindUsers(query: String) {
        _isLoading.value = true
        _isOnBoarding.value = false
        setResultMessage(getApplication(), 0, "")

        val client = ApiService.getApiService(getApplication()).searchUser(query)
        client.enqueue(object : Callback<UserSearchResponse> {
            override fun onResponse(
                call: Call<UserSearchResponse>,
                response: Response<UserSearchResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _isLoading.value = false
                        _data.postValue(responseBody.items)
                        if (responseBody.items.isNotEmpty()) {
                            _isNotFound.value = false
                            setResultMessage(getApplication(), responseBody.totalCount, query)
                            _isListVisible.value = true
                        } else {
                            _isNotFound.value = true
                            setResultMessage(getApplication(), 0, query)
                            _isListVisible.value = false
                        }
                    }
                }
            }

            override fun onFailure(call: Call<UserSearchResponse>, t: Throwable) {
                _isNotFound.value = true
                setResultMessage(getApplication(), 0, query)
                _isListVisible.value = false
            }
        })
    }

    private fun setResultMessage(context: Context, count: Int, query: String) {
        val formattedCount = NumberFormat.getInstance().format(count)
        _resultMessage.value = context.getString(R.string.text_result, formattedCount, query)
        if (count > 30) {
            _resultMessage.value += " " + context.getString(R.string.result_limited)
        }
    }
}
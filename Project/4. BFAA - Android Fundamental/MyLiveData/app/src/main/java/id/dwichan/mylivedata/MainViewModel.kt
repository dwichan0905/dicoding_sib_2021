package id.dwichan.mylivedata

import android.os.SystemClock
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class MainViewModel: ViewModel() {
    companion object {
        const val ONE_SECOND = 1000
    }

    private val mInitialTime = SystemClock.elapsedRealtime()
    private val mElapsedTime = MutableLiveData<Long?>()

    init {
        Timer().scheduleAtFixedRate(object: TimerTask() {
            override fun run() {
                val newValue = (SystemClock.elapsedRealtime() - mInitialTime) / ONE_SECOND
                mElapsedTime.postValue(newValue)
            }
        }, ONE_SECOND.toLong(), ONE_SECOND.toLong())
    }

    fun getElapsedTime(): LiveData<Long?> = mElapsedTime
}
package id.dwichan.myviewmodel

import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    var result = 0.0

    fun calculate(length: String, width: String, height: String) {
        result = length.toDouble() * width.toDouble() * height.toDouble()
    }

}
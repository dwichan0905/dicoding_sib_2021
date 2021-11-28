package id.dwichan.moviedicts.core.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log

object NetUtil {

    private const val TAG = "NetUtil"

    @Suppress("DEPRECATION")
    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        Log.i(TAG, "NetworkCapabilities.TRANSPORT_CELLULAR")
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        Log.i(TAG, "NetworkCapabilities.TRANSPORT_WIFI")
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        Log.i(TAG, "NetworkCapabilities.TRANSPORT_ETHERNET")
                        return true
                    }
                }
            }
        } else {
            // deprecated since Android N+
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }
}
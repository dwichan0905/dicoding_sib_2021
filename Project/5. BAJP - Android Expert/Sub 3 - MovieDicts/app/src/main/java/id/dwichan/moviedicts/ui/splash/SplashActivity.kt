package id.dwichan.moviedicts.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import id.dwichan.moviedicts.databinding.ActivitySplashBinding
import id.dwichan.moviedicts.ui.main.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    // fix memory leak
    private var _binding: ActivitySplashBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }, SPLASH_WAIT_TIME)
    }

    // fix memory leak
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val SPLASH_WAIT_TIME = 3000L
    }
}
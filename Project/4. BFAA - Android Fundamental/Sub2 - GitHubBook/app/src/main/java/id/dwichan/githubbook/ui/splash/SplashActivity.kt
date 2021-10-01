package id.dwichan.githubbook.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import id.dwichan.githubbook.databinding.ActivitySplashBinding
import id.dwichan.githubbook.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {

    companion object {
        const val DELAY_DURATION_IN_MILLIS = 3000L
    }

    private var _binding: ActivitySplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }, DELAY_DURATION_IN_MILLIS)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
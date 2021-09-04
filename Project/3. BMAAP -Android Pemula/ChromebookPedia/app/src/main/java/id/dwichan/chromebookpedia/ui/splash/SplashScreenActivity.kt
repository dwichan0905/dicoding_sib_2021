package id.dwichan.chromebookpedia.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import id.dwichan.chromebookpedia.databinding.ActivitySplashScreenBinding
import id.dwichan.chromebookpedia.ui.main.MainActivity

class SplashScreenActivity : AppCompatActivity() {

    private var _binding: ActivitySplashScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            val mainIntent = Intent(this, MainActivity::class.java)
            startActivity(mainIntent)
            finishAffinity()
        }, 2000)
    }

    override fun onDestroy() {
        super.onDestroy()
        this._binding = null
    }
}
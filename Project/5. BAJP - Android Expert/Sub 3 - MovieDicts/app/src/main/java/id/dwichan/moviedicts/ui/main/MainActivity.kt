package id.dwichan.moviedicts.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import id.dwichan.moviedicts.R
import id.dwichan.moviedicts.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    // fix memory leak
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.navigation_movies,
            R.id.navigation_television,
            R.id.navigation_bookmark,
            R.id.navigation_about
        ).build()

        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.bottomNavView.setupWithNavController(navController)

        supportActionBar?.elevation = 0f
    }

    // fix memory leak in Android 10+ after last activity finished
    override fun onBackPressed() {
        supportFinishAfterTransition()
    }

    // fix memory leak
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
package id.dwichan.moviedicts.ui.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.dwichan.moviedicts.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {

    private var _binding: ActivitySearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val EXTRA_SEARCH_MOVIES = "extra_search_movies"
        const val EXTRA_SEARCH_TV = "extra_search_tv"
        const val EXTRA_SEARCH_FAVORITE_MOVIES = "extra_search_favorites_movie"
        const val EXTRA_SEARCH_FAVORITE_TV = "extra_search_favorites_tv"
    }
}
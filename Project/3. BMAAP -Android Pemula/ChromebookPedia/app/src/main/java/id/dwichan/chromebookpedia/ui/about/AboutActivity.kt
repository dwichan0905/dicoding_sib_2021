package id.dwichan.chromebookpedia.ui.about

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import id.dwichan.chromebookpedia.R
import id.dwichan.chromebookpedia.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {

    private var _binding: ActivityAboutBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupActionBar()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) super.onBackPressed()
        return true
    }

    private fun setupActionBar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = getString(R.string.about)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
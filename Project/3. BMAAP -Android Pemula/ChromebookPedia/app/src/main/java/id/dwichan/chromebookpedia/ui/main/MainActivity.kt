package id.dwichan.chromebookpedia.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import id.dwichan.chromebookpedia.R
import id.dwichan.chromebookpedia.data.ChromebooksData
import id.dwichan.chromebookpedia.data.entity.Chromebook
import id.dwichan.chromebookpedia.databinding.ActivityMainBinding
import id.dwichan.chromebookpedia.ui.about.AboutActivity
import id.dwichan.chromebookpedia.ui.detail.DetailActivity

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupActionBar()
        showChromebookList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_about) {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupActionBar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = getString(R.string.app_name)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    private fun showChromebookList() {
        val chromebookAdapter = ChromebookAdapter()
        chromebookAdapter.setChromebooks(ChromebooksData.chromebooksList)
        chromebookAdapter.setOnItemActionListener(object : ChromebookAdapter.OnItemActionListener {
            override fun onItemClick(chromebook: Chromebook) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_CHROMEBOOK, chromebook)
                startActivity(intent)
            }
        })

        binding.listChromebooks.layoutManager = LinearLayoutManager(this)
        binding.listChromebooks.adapter = chromebookAdapter
    }
}
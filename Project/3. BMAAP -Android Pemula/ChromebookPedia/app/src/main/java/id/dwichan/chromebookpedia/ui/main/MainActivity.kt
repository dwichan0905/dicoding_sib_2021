package id.dwichan.chromebookpedia.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.dwichan.chromebookpedia.R
import id.dwichan.chromebookpedia.data.ChromebooksData
import id.dwichan.chromebookpedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.title = getString(R.string.app_name)

        showChromebookList()
    }

    private fun showChromebookList() {
        val chromebookAdapter = ChromebookAdapter()
        binding.listChromebooks.adapter = chromebookAdapter

        chromebookAdapter.setChromebooks(ChromebooksData.chromebooksList)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
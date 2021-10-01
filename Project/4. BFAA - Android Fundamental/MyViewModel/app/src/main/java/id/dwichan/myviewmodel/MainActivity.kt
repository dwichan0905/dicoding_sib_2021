package id.dwichan.myviewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import id.dwichan.myviewmodel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayResult()

        binding.apply {
            btnCalculate.setOnClickListener {
                when {
                    etWidth.text.isNullOrBlank() -> {
                        etWidth.error = getString(R.string.still_empty)
                    }
                    etHeight.text.isNullOrBlank() -> {
                        etHeight.error = getString(R.string.still_empty)
                    }
                    etLength.text.isNullOrBlank() -> {
                        etLength.error = getString(R.string.still_empty)
                    }
                    else -> {
                        viewModel.calculate(
                            etLength.text.toString(),
                            etWidth.text.toString(),
                            etHeight.text.toString()
                        )
                        displayResult()
                    }
                }
            }
        }
    }

    private fun displayResult() {
        binding.tvResult.text = viewModel.result.toString()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
package id.dwichan.chromebookpedia.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import com.bumptech.glide.Glide
import id.dwichan.chromebookpedia.R
import id.dwichan.chromebookpedia.data.entity.Chromebook
import id.dwichan.chromebookpedia.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_CHROMEBOOK = "extra_chromebook"
    }

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val chromebook = intent.getParcelableExtra<Chromebook>(EXTRA_CHROMEBOOK) as Chromebook
        applyData(chromebook)
    }

    private fun applyData(chromebook: Chromebook) {
        with (binding) {
            Glide.with(this@DetailActivity)
                .load(chromebook.image)
                .error(R.drawable.laptop)
                .into(imageLaptop)

            textLaptopName.text = chromebook.name
            textCompany.text = getString(R.string.company, chromebook.company)
            textDescription.text = chromebook.description
            textProcessors.text = chromebook.processor
            textMemories.text = chromebook.memory
            textStorages.text = chromebook.storage
            textDisplay.text = chromebook.display
            textGraphics.text = chromebook.graphic
            textColors.text = chromebook.color

            buttonShare.setOnClickListener {
                val shareIntent = ShareCompat.IntentBuilder(applicationContext).apply {
                    setChooserTitle(getString(R.string.share_to, chromebook.name))
                    setType("text/plain")
                    setText("""
                        *${chromebook.name}*
                        
                        ${chromebook.description}
                        
                        Terdapat varian warna: ${chromebook.color}
                    """.trimIndent())
                }.intent
                shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(shareIntent)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) super.onBackPressed()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
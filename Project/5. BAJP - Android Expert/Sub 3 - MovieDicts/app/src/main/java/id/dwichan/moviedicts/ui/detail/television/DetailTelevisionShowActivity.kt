package id.dwichan.moviedicts.ui.detail.television

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import id.dwichan.moviedicts.R
import id.dwichan.moviedicts.core.data.entity.*
import id.dwichan.moviedicts.core.util.Converter
import id.dwichan.moviedicts.core.util.IdlingResources
import id.dwichan.moviedicts.databinding.ActivityDetailTelevisionShowBinding
import id.dwichan.moviedicts.ui.loading.LoadingActivity
import id.dwichan.moviedicts.vo.Resource
import id.dwichan.moviedicts.vo.Status
import kotlin.math.floor

@AndroidEntryPoint
class DetailTelevisionShowActivity : AppCompatActivity() {

    private val viewModel: DetailTelevisionShowViewModel by viewModels()

    private lateinit var detailsResponse: TelevisionDetailsDataEntity
    private lateinit var creatorAdapter: CreatorAdapter
    private lateinit var productionCompanyAdapter: ProductionCompanyAdapter
    private lateinit var movieTelevisionDataEntity: MovieTelevisionDataEntity

    private lateinit var detailsObserver: Observer<Resource<TelevisionDetailsDataEntity>>

    // fix memory leak
    private var _binding: ActivityDetailTelevisionShowBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY)
        _binding = ActivityDetailTelevisionShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailsObserver = Observer { response ->
            if (response != null) {
                when (response.status) {
                    Status.SUCCESS -> {
                        showLoading(false)
                        if (response.data != null) {
                            detailsResponse = response.data
                            loadReceivedData()
                        } else {
                            Snackbar.make(
                                binding.root,
                                "An error occurred with unknown reason.",
                                Snackbar.LENGTH_SHORT
                            ).show()
                            supportFinishAfterTransition()
                        }
                    }
                    Status.ERROR -> {
                        showLoading(false)
                        Snackbar.make(
                            binding.root,
                            "An error occurred with reason: ${response.message}",
                            Snackbar.LENGTH_SHORT
                        ).show()
                        supportFinishAfterTransition()
                    }
                    Status.LOADING -> {
                        showLoading(true)
                    }
                }
            }
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.tv_show_details)
        supportActionBar?.subtitle = null
        supportActionBar?.elevation = 0f

        creatorAdapter = CreatorAdapter()
        productionCompanyAdapter = ProductionCompanyAdapter()

        binding.content.recCreator.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        binding.content.recCreator.adapter = creatorAdapter

        binding.content.recProductionCompany.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        binding.content.recProductionCompany.adapter = productionCompanyAdapter

        binding.content.buttonFavorite.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle(getString(R.string.coming_soon))
                .setMessage(R.string.coming_soon_message)
                .setPositiveButton(getString(R.string.okay), null)
                .create().show()
        }

        val bundle = intent.extras
        if (bundle != null) {
            movieTelevisionDataEntity = bundle.getParcelable<MovieTelevisionDataEntity>(
                EXTRA_TELEVISION_ENTITY
            ) as MovieTelevisionDataEntity

            viewModel.setTelevisionId(movieTelevisionDataEntity.id)
            viewModel.tvShowDetails.observe(this, detailsObserver)
        } else {
            Toast.makeText(
                this,
                "Details cannot loaded because of unknown issues!",
                Toast.LENGTH_SHORT
            ).show()
            supportFinishAfterTransition()
        }

        val bottomSheet = BottomSheetBehavior.from(binding.content.root)
        bottomSheet.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        binding.content.lottieSwipeIndicator.visibility = View.GONE
                        binding.content.textLottieSwipeIndicator.visibility = View.GONE
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        binding.content.lottieSwipeIndicator.visibility = View.VISIBLE
                        binding.content.textLottieSwipeIndicator.visibility = View.VISIBLE
                    }
                    BottomSheetBehavior.STATE_DRAGGING -> {}
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {}
                    BottomSheetBehavior.STATE_HIDDEN -> {}
                    BottomSheetBehavior.STATE_SETTLING -> {}
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                // do nothing
            }
        })
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            // show loading indicator
            val intentLoading = Intent(this, LoadingActivity::class.java)
            startActivity(intentLoading)
        } else {
            // close loading indicator
            IdlingResources.increment()
            Handler(Looper.getMainLooper()).postDelayed({
                val intentCloseLoading = Intent(LoadingActivity.INTENT_FINISH_LOADING)
                sendBroadcast(intentCloseLoading)
                IdlingResources.decrement()
            }, DELAY)
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun loadReceivedData() {
        detailsResponse.apply {
            // images
            binding.imageBackdrop.loadImage(this.backdropPath)
            binding.content.imageTvPoster.loadImage(this.posterPath)

            // title
            val tvName = this.name ?: this.originalName ?: "Unknown"
            binding.content.textTvTitle.text = tvName
            supportActionBar?.title = tvName

            // status
            val status = "Status: " + (this.status ?: "Unknown")
            supportActionBar?.subtitle = status

            // first air & duration
            val formattedDate = Converter.Date.reformatDateString(this.firstAirDate ?: "1970-01-01")
            val duration = this.episodeRunTime?.get(0) ?: 0
            val formattedDuration = Converter.Duration.minutesToString(duration.toLong())

            val durationStringBuilder = StringBuilder()
            durationStringBuilder.append(getString(R.string.first_air) + " ").append(formattedDate)
            if (duration != 0) {
                durationStringBuilder.append(" · ").append(formattedDuration)
            }
            binding.content.textFirstAirDuration.text = durationStringBuilder.toString()

            // genre
            val formattedGenres = Converter.TelevisionShow.listGenresToStringList(
                this.genres as List<GenresDataEntity>
            )
            binding.content.textGenres.text = formattedGenres

            // user score
            val userScore = this.voteAverage?.times(10)
            binding.content.pbUserScore.progress = floor(userScore ?: 0.0).toInt()
            binding.content.textUserScore.text = (this.voteAverage ?: 0.0).toString()

            // tagline
            if (this.tagline != null) {
                if (this.tagline != "") {
                    binding.content.textTagline.text = this.tagline
                } else {
                    binding.content.textTagline.visibility = View.GONE
                }
            } else {
                binding.content.textTagline.visibility = View.GONE
            }

            // episodes & seasons
            val episodeStringBuilder = StringBuilder()
            if (this.numberOfEpisodes != null) {
                episodeStringBuilder.append(this.numberOfEpisodes)
                if (this.numberOfEpisodes!! > 1) {
                    episodeStringBuilder.append(" episodes")
                } else {
                    episodeStringBuilder.append(" episode")
                }
            }
            if (this.numberOfSeasons != null) {
                if (this.numberOfSeasons!! > 0) {
                    episodeStringBuilder.append(", ").append(this.numberOfSeasons)
                    if (this.numberOfSeasons!! > 1) {
                        episodeStringBuilder.append(" seasons")
                    } else {
                        episodeStringBuilder.append(" season")
                    }
                }
            }
            binding.content.textEpisodes.text = episodeStringBuilder.toString()

            // overview
            binding.content.textOverview.text = this.overview ?: ""

            // creative teams
            if (this.createdBy != null) {
                if (this.createdBy!!.isNotEmpty()) {
                    creatorAdapter.setCreators(this.createdBy as List<CreatedByDataEntity>)
                } else {
                    binding.content.textLabelCreativeTeam.visibility = View.GONE
                    binding.content.recCreator.visibility = View.GONE
                }
            } else {
                binding.content.textLabelCreativeTeam.visibility = View.GONE
                binding.content.recCreator.visibility = View.GONE
            }

            // production companies
            productionCompanyAdapter.setCompanies(
                (this.productionCompanies ?: ArrayList()) as List<ProductionCompaniesDataEntity>
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_details, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> super.onBackPressed()
            R.id.menu_refresh -> {
                viewModel.tvShowDetails.observe(this, detailsObserver)
            }
            R.id.menu_share -> {
                val mimeType = "text/plain"
                ShareCompat.IntentBuilder(this).apply {
                    setType(mimeType)
                    setChooserTitle(getString(R.string.share_title_tv))
                    setText(
                        """
                        *${detailsResponse.name ?: detailsResponse.originalName ?: "Unknown TV Show"}*
                        ${binding.content.textFirstAirDuration.text}
                        Genre: ${binding.content.textGenres.text}
                        Vote: ${detailsResponse.voteAverage}
                        ${detailsResponse.tagline}
                        
                        ${detailsResponse.overview}
                    """.trimIndent()
                    )
                    startChooser()
                }
            }
        }
        return true
    }

    // fix memory leak
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val DELAY = 500L // 0.5s
        const val EXTRA_TELEVISION_ENTITY = "extra_television_detail_response"
    }
}

private fun ImageView.loadImage(url: String?) {
    Glide.with(this.context)
        .load("https://image.tmdb.org/t/p/original$url")
        .placeholder(R.drawable.ic_loading_image)
        .error(R.drawable.ic_error_image)
        .into(this)
}
package id.dwichan.moviedicts.ui.detail.movies

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import id.dwichan.moviedicts.MyApplication
import id.dwichan.moviedicts.R
import id.dwichan.moviedicts.core.data.entity.MovieTelevisionEntity
import id.dwichan.moviedicts.core.data.repository.remote.response.movie.MovieDetailsResponse
import id.dwichan.moviedicts.core.data.repository.remote.response.movie.MovieGenresItem
import id.dwichan.moviedicts.core.data.repository.remote.response.movie.ProductionCompaniesItem
import id.dwichan.moviedicts.core.util.Converter
import id.dwichan.moviedicts.core.util.movies.MoviesViewModelFactory
import id.dwichan.moviedicts.databinding.ActivityDetailMoviesBinding
import id.dwichan.moviedicts.ui.loading.LoadingActivity
import javax.inject.Inject
import kotlin.math.floor

class DetailMoviesActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: MoviesViewModelFactory

    private val viewModel: DetailMoviesViewModel by viewModels {
        factory
    }

    private lateinit var productionCompanyAdapter: ProductionCompanyAdapter
    private lateinit var movieDetailsResponse: MovieDetailsResponse
    private lateinit var movieTelevisionEntity: MovieTelevisionEntity

    // fix memory leak
    private var _binding: ActivityDetailMoviesBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.movie_details)
        supportActionBar?.subtitle = null
        supportActionBar?.elevation = 0f

        productionCompanyAdapter = ProductionCompanyAdapter()

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

        viewModel.data.observe(this) { response ->
            movieDetailsResponse = response
            if (response.genres != null) loadReceivedData()
        }

        viewModel.isLoading.observe(this) {
            if (it == true) {
                // show loading indicator
                val intentLoading = Intent(this, LoadingActivity::class.java)
                startActivity(intentLoading)
                binding.content.root.visibility = View.GONE
            } else {
                // close loading indicator
                val intentCloseLoading = Intent(LoadingActivity.INTENT_FINISH_LOADING)
                sendBroadcast(intentCloseLoading)
                binding.content.root.visibility = View.VISIBLE
            }
        }

        viewModel.errorReason.observe(this) { error ->
            error?.getContentIfNotHandled().let {
                if (it?.isNotEmpty() == true) {
                    Snackbar
                        .make(
                            binding.root,
                            "An error occurred with reason: $it",
                            Snackbar.LENGTH_SHORT
                        )
                        .show()
                }
            }
        }

        val bundle = intent.extras
        if (bundle != null) {
            movieTelevisionEntity = bundle.getParcelable<MovieTelevisionEntity>(
                EXTRA_MOVIE_ENTITY
            ) as MovieTelevisionEntity

            viewModel.setMovieId(movieTelevisionEntity.id)
            viewModel.fetchMovieDetails()
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
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                // do nothing
            }
        })
    }

    @Suppress("UNCHECKED_CAST")
    private fun loadReceivedData() {
        movieDetailsResponse.apply {
            // images
            binding.imageBackdrop.loadImage(this.backdropPath)
            binding.content.imageMoviePoster.loadImage(this.posterPath)

            // title
            val title = this.title ?: this.originalTitle ?: "Unknown"
            binding.content.textMovieTitle.text = title
            supportActionBar?.title = title

            // status
            val status = "Status: " + (this.status ?: "Unknown")
            supportActionBar?.subtitle = status

            // first air & duration
            val formattedDate = Converter.Date.reformatDateString(this.releaseDate ?: "1970-01-01")
            val duration = this.runtime ?: 0
            val formattedDuration = Converter.Duration.minutesToString(duration.toLong())

            val durationStringBuilder = StringBuilder()
            durationStringBuilder.append(getString(R.string.release_date) + " ")
                .append(formattedDate)
            if (duration != 0) {
                durationStringBuilder.append(" · ").append(formattedDuration)
            }
            binding.content.textReleasedDuration.text = durationStringBuilder.toString()

            // adult status
            if (this.adult != null) {
                if (this.adult) {
                    binding.content.textMovieAdultStatus.visibility = View.VISIBLE
                } else {
                    binding.content.textMovieAdultStatus.visibility = View.GONE
                }
            }

            // genre
            val formattedGenres = Converter.Movies.listGenresToStringList(
                this.genres as List<MovieGenresItem>
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

            // overview
            binding.content.textOverview.text = this.overview ?: ""

            // production companies
            productionCompanyAdapter.setCompanies(
                (this.productionCompanies ?: ArrayList()) as List<ProductionCompaniesItem>
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_details, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
            R.id.menu_refresh -> {
                viewModel.fetchMovieDetails()
            }
            R.id.menu_share -> {
                val mimeType = "text/plain"
                ShareCompat.IntentBuilder(this).apply {
                    setType(mimeType)
                    setChooserTitle(getString(R.string.share_title))
                    setText(
                        """
                        *${movieDetailsResponse.title}*
                        ${binding.content.textReleasedDuration.text}
                        Genre: ${binding.content.textGenres.text}
                        Vote: ${movieDetailsResponse.voteAverage}
                        ${movieDetailsResponse.tagline}
                        
                        ${movieDetailsResponse.overview}
                    """.trimIndent()
                    )
                    startChooser()
                }
            }
        }
        return true
    }

    override fun onBackPressed() {
        supportFinishAfterTransition()
    }

    // fix memory leak
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val EXTRA_MOVIE_ENTITY = "extra_movie_detail_response"
    }
}

private fun ImageView.loadImage(url: String?) {
    Glide.with(this.context)
        .load("https://image.tmdb.org/t/p/original$url")
        .placeholder(R.drawable.ic_loading_image)
        .error(R.drawable.ic_error_image)
        .into(this)
}
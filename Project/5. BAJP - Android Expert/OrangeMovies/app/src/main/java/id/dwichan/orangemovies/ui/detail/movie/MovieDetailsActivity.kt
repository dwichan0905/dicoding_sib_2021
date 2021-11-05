package id.dwichan.orangemovies.ui.detail.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.dwichan.orangemovies.R
import id.dwichan.orangemovies.data.Movie
import id.dwichan.orangemovies.ui.detail.crew.CrewAdapter
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var crewAdapter: CrewAdapter
    private lateinit var movieDetailsViewModel: MovieDetailsViewModel
    private var movieId: String = ""
    private lateinit var movie: Movie

    companion object {
        const val EXTRA_MOVIE_ID = "extra_movie_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = ""

        crewAdapter = CrewAdapter()
        rec_crews.layoutManager = LinearLayoutManager(this)
        rec_crews.adapter = crewAdapter

        val bundle = intent.extras!!
        movieId = bundle.getString(EXTRA_MOVIE_ID, "")

        movieDetailsViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
                .get(MovieDetailsViewModel::class.java)
        movieDetailsViewModel.setMovieId(movieId)
        movie = movieDetailsViewModel.getMovieData()!!

        Glide.with(this)
            .load(movie.poster)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_loading_24))
            .error(R.drawable.ic_baseline_error_24)
            .into(img_poster)
        tv_movie_name.text = movie.title
        tv_movie_certification.text = movie.certification
        tv_date.text = movie.releaseDate
        val categoryDuration = "${movie.category} - ${movie.duration}"
        tv_category_duration.text = categoryDuration
        pb_user_score.progress = movie.userScore
        val userScore = "${movie.userScore}%"
        tv_user_score.text = userScore
        tv_synopsis.text = movie.synopsis
        crewAdapter.setCrews(movieDetailsViewModel.getMovieCrews())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_details, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> super.onBackPressed()
            R.id.menu_share -> {
                val mimeType = "text/plain"
                ShareCompat.IntentBuilder.from(this).apply {
                    setType(mimeType)
                    setChooserTitle("Bagikan film ini sekarang!")
                    setText(resources.getString(R.string.share_text, movie.title))
                    startChooser()
                }
            }
        }
        return true
    }
}
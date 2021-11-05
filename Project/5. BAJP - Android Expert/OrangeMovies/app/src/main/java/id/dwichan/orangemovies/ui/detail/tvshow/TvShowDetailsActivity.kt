package id.dwichan.orangemovies.ui.detail.tvshow

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
import id.dwichan.orangemovies.data.TvShow
import id.dwichan.orangemovies.ui.detail.crew.CrewAdapter
import kotlinx.android.synthetic.main.activity_tv_show_details.*

class TvShowDetailsActivity : AppCompatActivity() {

    private lateinit var crewAdapter: CrewAdapter
    private lateinit var tvShow: TvShow
    private var tvId: String = ""
    private lateinit var tvShowDetailsViewModel: TvShowDetailsViewModel

    companion object {
        const val EXTRA_TV_SHOW_ID = "extra_tv_show_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_show_details)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = ""

        crewAdapter = CrewAdapter()
        rec_crews.layoutManager = LinearLayoutManager(this)
        rec_crews.adapter = crewAdapter

        val bundle = intent.extras!!
        tvId = bundle.getString(EXTRA_TV_SHOW_ID, "")

        tvShowDetailsViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(TvShowDetailsViewModel::class.java)
        tvShowDetailsViewModel.setTvShowId(tvId)
        tvShow = tvShowDetailsViewModel.getTvShowData()

        Glide.with(this)
            .load(tvShow.poster)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_loading_24))
            .error(R.drawable.ic_baseline_error_24)
            .into(img_poster)
        tv_tv_show_name.text = tvShow.title
        tv_tv_show_certification.text = tvShow.certification
        tv_year.text = tvShow.year.toString()
        val categoryDuration = "${tvShow.category} - ${tvShow.duration}"
        tv_category_duration.text = categoryDuration
        pb_user_score.progress = tvShow.userScore
        val userScore = "${tvShow.userScore}%"
        tv_user_score.text = userScore
        tv_synopsis.text = tvShow.synopsis
        crewAdapter.setCrews(tvShow.crews)
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
                    setChooserTitle("Bagikan acara televisi ini sekarang!")
                    setText(resources.getString(R.string.share_text, tvShow.title))
                    startChooser()
                }
            }
        }
        return true
    }
}
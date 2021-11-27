package id.dwichan.moviedicts.ui.main.movies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.dwichan.moviedicts.R
import id.dwichan.moviedicts.core.data.entity.MovieTelevisionDataEntity
import id.dwichan.moviedicts.core.data.entity.TrendingResultsDataEntity
import id.dwichan.moviedicts.databinding.FragmentMoviesBinding
import id.dwichan.moviedicts.databinding.ItemMoviesTrendingBinding
import id.dwichan.moviedicts.ui.detail.movies.DetailMoviesActivity
import id.dwichan.moviedicts.vo.Resource
import id.dwichan.moviedicts.vo.Status
import id.dwichan.moviedicts.vo.Type

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    // fix memory leak
    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TrendingMoviesViewModel by viewModels()

    private val itemAction = object : TrendingMoviesAdapter.OnItemActionListener {
        override fun onItemClick(
            position: Int,
            itemBind: ItemMoviesTrendingBinding,
            item: TrendingResultsDataEntity
        ) {
            val dataSend = MovieTelevisionDataEntity(
                id = item.id ?: 0,
                title = item.title ?: item.originalTitle ?: item.name ?: item.originalName
                ?: getString(R.string.unknown),
                backdropPath = item.backdropPath ?: "/",
                posterPath = item.posterPath ?: "/",
                mediaType = Type.MEDIA_TYPE_MOVIES
            )

            val intent = Intent(context, DetailMoviesActivity::class.java)
            intent.putExtra(DetailMoviesActivity.EXTRA_MOVIE_ENTITY, dataSend)
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                requireActivity(), itemBind.imageMoviePoster, "posterIcon"
            )
            startActivity(intent, options.toBundle())
        }
    }

    private lateinit var trendingTodayObserver:
            Observer<Resource<List<TrendingResultsDataEntity>>>

    private lateinit var trendingWeeklyObserver:
            Observer<Resource<List<TrendingResultsDataEntity>>>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    // FIXME: Repair these errors and change it to Status like TrendingToday
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val trendingTodayAdapter = TrendingMoviesAdapter()
        val trendingWeeklyAdapter = TrendingMoviesAdapter()

        binding.apply {
            recMoviesTrendingToday.layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.HORIZONTAL, false
            )
            recMoviesTrendingToday.adapter = trendingTodayAdapter
            recMoviesTrendingWeekly.layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.HORIZONTAL, false
            )
            recMoviesTrendingWeekly.adapter = trendingWeeklyAdapter

            trendingTodayObserver = Observer { response ->
                if (response != null) {
                    when (response.status) {
                        Status.SUCCESS -> {
                            animLoadingTrendingToday.visibility = View.GONE
                            showError(false)
                            showContent(true)
                            trendingTodayAdapter.setItems(response.data ?: ArrayList())
                            trendingTodayAdapter.itemAction = itemAction
                        }
                        Status.ERROR -> {
                            animLoadingTrendingToday.visibility = View.GONE
                            showError(true, response.message)
                            showContent(false)
                        }
                        Status.LOADING -> {
                            animLoadingTrendingToday.visibility = View.VISIBLE
                            showError(false)
                            showContent(false)
                        }
                    }
                }
            }

            trendingWeeklyObserver = Observer { response ->
                if (response != null) {
                    when (response.status) {
                        Status.SUCCESS -> {
                            animLoadingTrendingWeekly.visibility = View.GONE
                            showError(false)
                            showContent(true)
                            trendingWeeklyAdapter.setItems(response.data ?: ArrayList())
                            trendingWeeklyAdapter.itemAction = itemAction
                        }
                        Status.ERROR -> {
                            animLoadingTrendingWeekly.visibility = View.GONE
                            showError(true, response.message)
                            showContent(false)
                        }
                        Status.LOADING -> {
                            animLoadingTrendingWeekly.visibility = View.VISIBLE
                            showError(false)
                            showContent(false)
                        }
                    }
                }
            }

            viewModel.trendingToday.observe(viewLifecycleOwner, trendingTodayObserver)

            viewModel.trendingWeekly.observe(viewLifecycleOwner, trendingWeeklyObserver)

            fragmentMovie.setOnRefreshListener {
                viewModel.trendingToday.observe(viewLifecycleOwner, trendingTodayObserver)
                viewModel.trendingWeekly.observe(viewLifecycleOwner, trendingWeeklyObserver)
                fragmentMovie.isRefreshing = false
            }
        }
    }

    private fun showError(state: Boolean, message: String? = "") {
        binding.apply {
            if (state) {
                val reasonText = """
                    An error were occurred. We will fix it immediately!
                    Reason: $message
                """.trimIndent()
                animError.visibility = View.VISIBLE
                textError.visibility = View.VISIBLE
                textError.text = reasonText
            } else {
                animError.visibility = View.GONE
                textError.visibility = View.GONE
                textError.text = ""
            }
        }
    }

    private fun showContent(state: Boolean) {
        binding.apply {
            if (state) {
                textTrendingToday.visibility = View.VISIBLE
                textTrendingTodayDesc.visibility = View.VISIBLE
                recMoviesTrendingToday.visibility = View.VISIBLE
                textTrendingWeekly.visibility = View.VISIBLE
                textTrendingWeeklyDesc.visibility = View.VISIBLE
                recMoviesTrendingWeekly.visibility = View.VISIBLE
            } else {
                textTrendingToday.visibility = View.GONE
                textTrendingTodayDesc.visibility = View.GONE
                recMoviesTrendingToday.visibility = View.GONE
                textTrendingWeekly.visibility = View.GONE
                textTrendingWeeklyDesc.visibility = View.GONE
                recMoviesTrendingWeekly.visibility = View.GONE
            }
        }
    }

    // fix memory leak
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
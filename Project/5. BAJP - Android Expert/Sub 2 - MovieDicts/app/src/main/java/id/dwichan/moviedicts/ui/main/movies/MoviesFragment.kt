package id.dwichan.moviedicts.ui.main.movies

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import id.dwichan.moviedicts.MyApplication
import id.dwichan.moviedicts.R
import id.dwichan.moviedicts.core.data.entity.MovieTelevisionEntity
import id.dwichan.moviedicts.core.data.repository.remote.response.trending.TrendingResultsItem
import id.dwichan.moviedicts.core.util.movies.MoviesViewModelFactory
import id.dwichan.moviedicts.databinding.FragmentMoviesBinding
import id.dwichan.moviedicts.databinding.ItemMoviesTrendingBinding
import id.dwichan.moviedicts.ui.detail.movies.DetailMoviesActivity
import javax.inject.Inject

class MoviesFragment : Fragment() {

    // fix memory leak
    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var factory: MoviesViewModelFactory

    private val viewModel: TrendingMoviesViewModel by viewModels {
        factory
    }

    private val itemAction = object : TrendingMoviesAdapter.OnItemActionListener {
        override fun onItemClick(
            position: Int,
            itemBind: ItemMoviesTrendingBinding,
            item: TrendingResultsItem
        ) {
            val dataSend = MovieTelevisionEntity(
                id = item.id ?: 0,
                title = item.title ?: item.originalTitle ?: item.name ?: item.originalName
                ?: getString(R.string.unknown),
                backdropPath = item.backdropPath ?: "/",
                posterPath = item.posterPath ?: "/",
                mediaType = "movie"
            )

            val intent = Intent(context, DetailMoviesActivity::class.java)
            intent.putExtra(DetailMoviesActivity.EXTRA_MOVIE_ENTITY, dataSend)
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                requireActivity(), itemBind.imageMoviePoster, "posterIcon"
            )
            startActivity(intent, options.toBundle())
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

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

            viewModel.isLoadingToday.observe(viewLifecycleOwner) { state ->
                if (state) {
                    animLoadingTrendingToday.visibility = View.VISIBLE
                } else {
                    animLoadingTrendingToday.visibility = View.GONE
                }
            }

            viewModel.isLoadingWeekly.observe(viewLifecycleOwner) { state ->
                if (state) {
                    animLoadingTrendingWeekly.visibility = View.VISIBLE
                } else {
                    animLoadingTrendingWeekly.visibility = View.GONE
                }
            }

            viewModel.errorReason.observe(viewLifecycleOwner) {
                it.getContentIfNotHandled().let { reason ->
                    if (reason?.isNotEmpty() == true) {
                        val reasonText = "An error occurred with reason: $reason"
                        animError.visibility = View.VISIBLE
                        textError.visibility = View.VISIBLE
                        textError.text = reasonText

                        showContent(false)
                    } else {
                        animError.visibility = View.GONE
                        textError.visibility = View.GONE
                        textError.text = ""

                        showContent(true)
                    }
                }
            }

            binding.fragmentMovie.setOnRefreshListener {
                viewModel.fetchTrendingToday()
                viewModel.fetchTrendingWeekly()
                fragmentMovie.isRefreshing = false
            }
        }

        viewModel.trendingToday.observe(viewLifecycleOwner) { data ->
            trendingTodayAdapter.setItems(data)
            trendingTodayAdapter.itemAction = itemAction
        }

        viewModel.trendingWeekly.observe(viewLifecycleOwner) { data ->
            trendingWeeklyAdapter.setItems(data)
            trendingWeeklyAdapter.itemAction = itemAction
        }

        viewModel.fetchTrendingToday()
        viewModel.fetchTrendingWeekly()
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
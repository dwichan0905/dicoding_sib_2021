package id.dwichan.moviedicts.ui.main.movies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import id.dwichan.moviedicts.data.entity.MovieTelevisionEntity
import id.dwichan.moviedicts.data.repository.remote.response.trending.TrendingResultsItem
import id.dwichan.moviedicts.databinding.FragmentMoviesBinding
import id.dwichan.moviedicts.databinding.ItemMoviesTrendingBinding
import id.dwichan.moviedicts.ui.detail.movies.DetailMoviesActivity

class MoviesFragment : Fragment() {

    private val viewModel: TrendingMoviesViewModel by viewModels()

    private val itemAction = object : TrendingMoviesAdapter.OnItemActionListener {
        override fun onItemClick(
            position: Int,
            itemBind: ItemMoviesTrendingBinding,
            item: TrendingResultsItem
        ) {
            val dataSend = MovieTelevisionEntity(
                id = item.id ?: 0,
                title = item.title ?: item.originalTitle ?: item.name ?: item.originalName ?: "Unknown",
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

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

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
        }

        viewModel.isLoadingToday.observe(viewLifecycleOwner) { state ->
            if (state) {
                binding.animLoadingTrendingToday.visibility = View.VISIBLE
            } else {
                binding.animLoadingTrendingToday.visibility = View.GONE
            }
        }

        viewModel.trendingToday.observe(viewLifecycleOwner) { data ->
            trendingTodayAdapter.setItems(data)
            trendingTodayAdapter.itemAction = itemAction
        }

        viewModel.isLoadingWeekly.observe(viewLifecycleOwner) { state ->
            if (state) {
                binding.animLoadingTrendingWeekly.visibility = View.VISIBLE
            } else {
                binding.animLoadingTrendingWeekly.visibility = View.GONE
            }
        }

        viewModel.trendingWeekly.observe(viewLifecycleOwner) { data ->
            trendingWeeklyAdapter.setItems(data)
            trendingWeeklyAdapter.itemAction = itemAction
        }

        viewModel.errorReason.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled().let { reason ->
                if (reason?.isNotEmpty() == true) {
                    val reasonText = "An error occurred with reason: $reason"
                    binding.animError.visibility = View.VISIBLE
                    binding.textError.visibility = View.VISIBLE
                    binding.textError.text = reasonText

                    showContent(false)
                } else {
                    binding.animError.visibility = View.GONE
                    binding.textError.visibility = View.GONE
                    binding.textError.text = ""

                    showContent(true)
                }
            }
        }

        viewModel.fetchTrendingToday()
        viewModel.fetchTrendingWeekly()

        binding.fragmentMovie.setOnRefreshListener {
            viewModel.fetchTrendingToday()
            viewModel.fetchTrendingWeekly()
            binding.fragmentMovie.isRefreshing = false
        }
    }

    private fun showContent(state: Boolean) {
        if (state) {
            binding.textTrendingToday.visibility = View.VISIBLE
            binding.textTrendingTodayDesc.visibility = View.VISIBLE
            binding.recMoviesTrendingToday.visibility = View.VISIBLE
            binding.textTrendingWeekly.visibility = View.VISIBLE
            binding.textTrendingWeeklyDesc.visibility = View.VISIBLE
            binding.recMoviesTrendingWeekly.visibility = View.VISIBLE
        } else {
            binding.textTrendingToday.visibility = View.GONE
            binding.textTrendingTodayDesc.visibility = View.GONE
            binding.recMoviesTrendingToday.visibility = View.GONE
            binding.textTrendingWeekly.visibility = View.GONE
            binding.textTrendingWeeklyDesc.visibility = View.GONE
            binding.recMoviesTrendingWeekly.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
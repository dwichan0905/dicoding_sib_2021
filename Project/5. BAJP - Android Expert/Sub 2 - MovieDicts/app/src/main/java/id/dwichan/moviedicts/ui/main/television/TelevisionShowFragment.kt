package id.dwichan.moviedicts.ui.main.television

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.dwichan.moviedicts.R
import id.dwichan.moviedicts.data.entity.MovieTelevisionEntity
import id.dwichan.moviedicts.data.repository.remote.response.trending.TrendingResultsItem
import id.dwichan.moviedicts.databinding.FragmentTelevisionShowBinding
import id.dwichan.moviedicts.databinding.ItemTvShowsTrendingBinding
import id.dwichan.moviedicts.ui.detail.television.DetailTelevisionShowActivity
import id.dwichan.moviedicts.util.television.TelevisionShowViewModelFactory

class TelevisionShowFragment : Fragment() {

    private lateinit var viewModel: TrendingTelevisionShowViewModel

    // fix memory leak
    private var _binding: FragmentTelevisionShowBinding? = null
    private val binding get() = _binding!!

    private val onItemAction = object : TrendingTelevisionShowAdapter.OnItemActionListener {
        override fun onItemClick(
            position: Int,
            itemBind: ItemTvShowsTrendingBinding,
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

            val intent = Intent(context, DetailTelevisionShowActivity::class.java)
            intent.putExtra(DetailTelevisionShowActivity.EXTRA_TELEVISION_ENTITY, dataSend)
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                requireActivity(), itemBind.imageMoviePoster, "posterIcon"
            )
            startActivity(intent, options.toBundle())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTelevisionShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val trendingToday = TrendingTelevisionShowAdapter()
        val trendingWeekly = TrendingTelevisionShowAdapter()

        val factory = TelevisionShowViewModelFactory.getInstance()
        viewModel = ViewModelProvider(this, factory)[
                TrendingTelevisionShowViewModel::class.java
        ]

        binding.apply {
            recTvTrendingToday.layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.HORIZONTAL, false
            )
            recTvTrendingToday.adapter = trendingToday
            recTvTrendingWeekly.layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.HORIZONTAL, false
            )
            recTvTrendingWeekly.adapter = trendingWeekly

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

            fragmentTelevisionShow.setOnRefreshListener {
                viewModel.fetchTrendingToday()
                viewModel.fetchTrendingWeekly()
                fragmentTelevisionShow.isRefreshing = false
            }
        }

        viewModel.trendingToday.observe(viewLifecycleOwner) { data ->
            trendingToday.setItems(data)
            trendingToday.itemAction = onItemAction
        }

        viewModel.trendingWeekly.observe(viewLifecycleOwner) { data ->
            trendingWeekly.setItems(data)
            trendingWeekly.itemAction = onItemAction
        }

        viewModel.fetchTrendingToday()
        viewModel.fetchTrendingWeekly()
    }

    private fun showContent(state: Boolean) {
        binding.apply {
            if (state) {
                textTrendingToday.visibility = View.VISIBLE
                textTrendingTodayDesc.visibility = View.VISIBLE
                recTvTrendingToday.visibility = View.VISIBLE
                textTrendingWeekly.visibility = View.VISIBLE
                textTrendingWeeklyDesc.visibility = View.VISIBLE
                recTvTrendingWeekly.visibility = View.VISIBLE
            } else {
                textTrendingToday.visibility = View.GONE
                textTrendingTodayDesc.visibility = View.GONE
                recTvTrendingToday.visibility = View.GONE
                textTrendingWeekly.visibility = View.GONE
                textTrendingWeeklyDesc.visibility = View.GONE
                recTvTrendingWeekly.visibility = View.GONE
            }
        }
    }

    // fix memory leak
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
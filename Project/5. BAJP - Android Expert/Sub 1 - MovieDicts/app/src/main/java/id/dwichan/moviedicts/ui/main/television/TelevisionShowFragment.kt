package id.dwichan.moviedicts.ui.main.television

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
import id.dwichan.moviedicts.databinding.FragmentTelevisionShowBinding
import id.dwichan.moviedicts.databinding.ItemTvShowsTrendingBinding
import id.dwichan.moviedicts.ui.detail.television.DetailTelevisionShowActivity

class TelevisionShowFragment : Fragment() {

    private val viewModel: TrendingTelevisionShowViewModel by viewModels()

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
                title = item.title ?: item.originalTitle ?: item.name ?: item.originalName ?: "Unknown",
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

        binding.apply {
            recTvTrendingToday.layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.HORIZONTAL, false
            )
            recTvTrendingToday.adapter = trendingToday
            recTvTrendingWeekly.layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.HORIZONTAL, false
            )
            recTvTrendingWeekly.adapter = trendingWeekly
        }

        viewModel.isLoadingToday.observe(viewLifecycleOwner) { state ->
            if (state) {
                binding.animLoadingTrendingToday.visibility = View.VISIBLE
            } else {
                binding.animLoadingTrendingToday.visibility = View.GONE
            }
        }

        viewModel.trendingToday.observe(viewLifecycleOwner) { data ->
            trendingToday.setItems(data)
            trendingToday.itemAction = onItemAction
        }

        viewModel.isLoadingWeekly.observe(viewLifecycleOwner) { state ->
            if (state) {
                binding.animLoadingTrendingWeekly.visibility = View.VISIBLE
            } else {
                binding.animLoadingTrendingWeekly.visibility = View.GONE
            }
        }

        viewModel.trendingWeekly.observe(viewLifecycleOwner) { data ->
            trendingWeekly.setItems(data)
            trendingWeekly.itemAction = onItemAction
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

        binding.fragmentTelevisionShow.setOnRefreshListener {
            viewModel.fetchTrendingToday()
            viewModel.fetchTrendingWeekly()
            binding.fragmentTelevisionShow.isRefreshing = false
        }
    }

    private fun showContent(state: Boolean) {
        if (state) {
            binding.textTrendingToday.visibility = View.VISIBLE
            binding.textTrendingTodayDesc.visibility = View.VISIBLE
            binding.recTvTrendingToday.visibility = View.VISIBLE
            binding.textTrendingWeekly.visibility = View.VISIBLE
            binding.textTrendingWeeklyDesc.visibility = View.VISIBLE
            binding.recTvTrendingWeekly.visibility = View.VISIBLE
        } else {
            binding.textTrendingToday.visibility = View.GONE
            binding.textTrendingTodayDesc.visibility = View.GONE
            binding.recTvTrendingToday.visibility = View.GONE
            binding.textTrendingWeekly.visibility = View.GONE
            binding.textTrendingWeeklyDesc.visibility = View.GONE
            binding.recTvTrendingWeekly.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
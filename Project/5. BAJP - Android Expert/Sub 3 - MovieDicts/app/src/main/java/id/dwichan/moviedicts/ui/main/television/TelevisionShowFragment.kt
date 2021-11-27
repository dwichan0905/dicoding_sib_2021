package id.dwichan.moviedicts.ui.main.television

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
import id.dwichan.moviedicts.databinding.FragmentTelevisionShowBinding
import id.dwichan.moviedicts.databinding.ItemTvShowsTrendingBinding
import id.dwichan.moviedicts.ui.detail.television.DetailTelevisionShowActivity
import id.dwichan.moviedicts.vo.Resource
import id.dwichan.moviedicts.vo.Status
import id.dwichan.moviedicts.vo.Type

@AndroidEntryPoint
class TelevisionShowFragment : Fragment() {

    private val viewModel: TrendingTelevisionShowViewModel by viewModels()

    // fix memory leak
    private var _binding: FragmentTelevisionShowBinding? = null
    private val binding get() = _binding!!

    private val onItemAction = object : TrendingTelevisionShowAdapter.OnItemActionListener {
        override fun onItemClick(
            position: Int,
            itemBind: ItemTvShowsTrendingBinding,
            item: TrendingResultsDataEntity
        ) {
            val dataSend = MovieTelevisionDataEntity(
                id = item.id ?: 0,
                title = item.title ?: item.originalTitle ?: item.name ?: item.originalName
                ?: getString(R.string.unknown),
                backdropPath = item.backdropPath ?: "/",
                posterPath = item.posterPath ?: "/",
                mediaType = Type.MEDIA_TYPE_TELEVISION
            )

            val intent = Intent(context, DetailTelevisionShowActivity::class.java)
            intent.putExtra(DetailTelevisionShowActivity.EXTRA_TELEVISION_ENTITY, dataSend)
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
        _binding = FragmentTelevisionShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    // FIXME: Repair these errors, change the ViewModel like MoviesFragment does!
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

            trendingTodayObserver = Observer { response ->
                if (response != null) {
                    when (response.status) {
                        Status.SUCCESS -> {
                            animLoadingTrendingToday.visibility = View.GONE
                            showContent(true)
                            showError(false)
                            trendingToday.setItems(response.data ?: ArrayList())
                            trendingToday.itemAction = onItemAction
                        }
                        Status.ERROR -> {
                            animLoadingTrendingToday.visibility = View.GONE
                            showContent(false)
                            showError(true, response.message)
                        }
                        Status.LOADING -> {
                            animLoadingTrendingToday.visibility = View.VISIBLE
                            showContent(false)
                            showError(false)
                        }
                    }
                }
            }

            trendingWeeklyObserver = Observer { response ->
                if (response != null) {
                    when (response.status) {
                        Status.SUCCESS -> {
                            animLoadingTrendingWeekly.visibility = View.GONE
                            showContent(true)
                            trendingWeekly.setItems(response.data ?: ArrayList())
                            trendingWeekly.itemAction = onItemAction
                        }
                        Status.ERROR -> {
                            animLoadingTrendingWeekly.visibility = View.GONE
                            showContent(false)
                        }
                        Status.LOADING -> {
                            animLoadingTrendingWeekly.visibility = View.VISIBLE
                            showContent(false)
                        }
                    }
                }
            }

            viewModel.trendingToday.observe(viewLifecycleOwner, trendingTodayObserver)

            viewModel.trendingWeekly.observe(viewLifecycleOwner, trendingWeeklyObserver)

            fragmentTelevisionShow.setOnRefreshListener {
                viewModel.trendingToday.observe(viewLifecycleOwner, trendingTodayObserver)
                viewModel.trendingWeekly.observe(viewLifecycleOwner, trendingWeeklyObserver)
                fragmentTelevisionShow.isRefreshing = false
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
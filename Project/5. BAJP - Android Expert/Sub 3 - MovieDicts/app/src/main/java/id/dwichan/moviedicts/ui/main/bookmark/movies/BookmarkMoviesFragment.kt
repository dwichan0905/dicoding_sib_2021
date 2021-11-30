package id.dwichan.moviedicts.ui.main.bookmark.movies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.dwichan.moviedicts.core.data.entity.MovieTelevisionDataEntity
import id.dwichan.moviedicts.databinding.FragmentBookmarkMoviesBinding
import id.dwichan.moviedicts.databinding.ItemBookmarkBinding
import id.dwichan.moviedicts.ui.detail.movies.DetailMoviesActivity

@AndroidEntryPoint
class BookmarkMoviesFragment : Fragment() {

    private val viewModel: BookmarkMoviesViewModel by viewModels()
    private lateinit var adapter: BookmarkMoviesAdapter

    private var _binding: FragmentBookmarkMoviesBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookmarkMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            adapter = BookmarkMoviesAdapter()
            recMovieBookmarks.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            recMovieBookmarks.adapter = adapter
            adapter.itemAction = object : BookmarkMoviesAdapter.OnItemActionListener {
                override fun onItemClick(
                    position: Int,
                    itemBind: ItemBookmarkBinding,
                    item: MovieTelevisionDataEntity
                ) {
                    val intent = Intent(context, DetailMoviesActivity::class.java)
                    intent.putExtra(DetailMoviesActivity.EXTRA_MOVIE_ENTITY, item)
                    val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        requireActivity(), itemBind.imageMoviePoster, "posterIcon"
                    )
                    startActivity(intent, options.toBundle())
                }
            }

            viewModel.bookmarkList.observe(viewLifecycleOwner) { data ->
                if (data.isEmpty()) {
                    showNotFoundMessage(true)
                    adapter.setBookmarkList(ArrayList())
                } else {
                    showNotFoundMessage(false)
                    adapter.setBookmarkList(data)
                }
            }
        }
    }

    private fun showNotFoundMessage(state: Boolean) {
        binding.apply {
            if (state) {
                animMovieNotFound.visibility = View.VISIBLE
                textMovieNotFound.visibility = View.VISIBLE
                animMovieNotFound.progress = 0f
                animMovieNotFound.playAnimation()
            } else {
                animMovieNotFound.visibility = View.GONE
                textMovieNotFound.visibility = View.GONE
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.reload()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
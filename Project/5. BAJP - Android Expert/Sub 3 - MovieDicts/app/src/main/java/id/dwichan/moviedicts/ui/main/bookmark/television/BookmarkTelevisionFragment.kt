package id.dwichan.moviedicts.ui.main.bookmark.television

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
import id.dwichan.moviedicts.databinding.FragmentBookmarkTelevisionBinding
import id.dwichan.moviedicts.databinding.ItemBookmarkBinding
import id.dwichan.moviedicts.ui.detail.television.DetailTelevisionShowActivity

@AndroidEntryPoint
class BookmarkTelevisionFragment : Fragment() {

    private val viewModel: BookmarkTelevisionViewModel by viewModels()

    private var _binding: FragmentBookmarkTelevisionBinding? = null
    val binding get() = _binding

    private var adapter: BookmarkTelevisionAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookmarkTelevisionBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            if (this != null) {
                adapter = BookmarkTelevisionAdapter()
                recTvBookmarks.layoutManager =
                    StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                recTvBookmarks.adapter = adapter
                adapter?.itemAction = object : BookmarkTelevisionAdapter.OnItemActionListener {
                    override fun onItemClick(
                        position: Int,
                        itemBind: ItemBookmarkBinding,
                        item: MovieTelevisionDataEntity
                    ) {
                        val intent = Intent(context, DetailTelevisionShowActivity::class.java)
                        intent.putExtra(DetailTelevisionShowActivity.EXTRA_TELEVISION_ENTITY, item)
                        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                            requireActivity(), itemBind.imageMoviePoster, "posterIcon"
                        )
                        startActivity(intent, options.toBundle())
                    }
                }

                viewModel.bookmarkList.observe(viewLifecycleOwner) { data ->
                    if (data.isEmpty()) {
                        showNotFoundMessage(true)
                    } else {
                        showNotFoundMessage(false)
                    }
                    adapter?.submitList(data)
                }
            }
        }
    }

    private fun showNotFoundMessage(state: Boolean) {
        binding.apply {
            if (this != null) {
                if (state) {
                    animTvNotFound.visibility = View.VISIBLE
                    textTvNotFound.visibility = View.VISIBLE
                    animTvNotFound.progress = 0f
                    animTvNotFound.playAnimation()
                } else {
                    animTvNotFound.visibility = View.GONE
                    textTvNotFound.visibility = View.GONE
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.reload()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter?.itemAction = null
        adapter = null
        _binding = null
    }

}
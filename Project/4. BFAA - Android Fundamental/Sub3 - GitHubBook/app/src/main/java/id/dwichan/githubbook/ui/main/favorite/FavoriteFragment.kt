package id.dwichan.githubbook.ui.main.favorite

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import id.dwichan.githubbook.R
import id.dwichan.githubbook.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private val comingSoon = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setLoading(false)
        setListVisible()
        setEmptyVisibility(true)

        requireActivity().invalidateOptionsMenu()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main_fragments, menu)

        val searchManager = activity?.getSystemService(SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView
        searchView.apply {
            setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
            queryHint = "Find your favorite user..."
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    Toast.makeText(context, query, Toast.LENGTH_SHORT).show()
                    setLoading(true)
                    setEmptyVisibility(false)
                    return true
                }

                override fun onQueryTextChange(newText: String): Boolean = false
            })
        }
    }

    private fun setEmptyVisibility(state: Boolean) {
        binding.layoutEmpty.root.isVisible = state
    }

    private fun setLoading(state: Boolean) {
        binding.progressBar.isVisible = state
    }

    private fun setListVisible() {
        binding.listUsers.isVisible = comingSoon
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val SEARCH_SERVICE: String = Context.SEARCH_SERVICE
    }

}
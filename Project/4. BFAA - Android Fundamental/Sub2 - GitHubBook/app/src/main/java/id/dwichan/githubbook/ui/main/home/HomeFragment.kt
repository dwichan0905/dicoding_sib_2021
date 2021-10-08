package id.dwichan.githubbook.ui.main.home

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import id.dwichan.githubbook.R
import id.dwichan.githubbook.data.network.response.UserItem
import id.dwichan.githubbook.databinding.FragmentHomeBinding
import id.dwichan.githubbook.databinding.ItemUsersBinding
import id.dwichan.githubbook.ui.detail.DetailActivity
import id.dwichan.githubbook.ui.main.UsersAdapter
import id.dwichan.githubbook.util.Convert
import kotlin.math.floor

class HomeFragment : Fragment() {

    companion object {
        private const val SEARCH_SERVICE: String = Context.SEARCH_SERVICE
    }

    private val viewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var querySearch: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.isLoading.observe(viewLifecycleOwner) { state ->
            setLoading(state, querySearch)
            if (state == true) setListVisible(false)
        }
        viewModel.isOnBoarding.observe(viewLifecycleOwner) { state ->
            setOnBoardingVisibility(state)
        }
        viewModel.isListVisible.observe(viewLifecycleOwner) { state ->
            setListVisible(state)
        }
        viewModel.isNotFound.observe(viewLifecycleOwner) { state ->
            setNotFoundVisibility(state, querySearch)
        }
        viewModel.data.observe(viewLifecycleOwner) { listUsers ->
            showUsersList(listUsers)
        }
        viewModel.resultMessage.observe(viewLifecycleOwner) { message ->
            binding.textResult.text = message
        }
    }

    private fun showUsersList(listItem: List<UserItem>) {
        with(binding) {
            val adapter = UsersAdapter()
            val divider = MaterialDividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
            divider.dividerInsetStart = floor(Convert.dpToPx(requireContext(), 80)).toInt()
            listUsers.addItemDecoration(divider)
            listUsers.layoutManager = LinearLayoutManager(context)
            listUsers.adapter = adapter
            adapter.setUserList(listItem)
            adapter.onItemAction = object : UsersAdapter.OnItemActionListener {
                override fun onClick(item: UserItem, itemBinding: ItemUsersBinding) {
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_USER_ITEM, item)
                    val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        requireActivity(), itemBinding.imageUser, "UserIcon"
                    )
                    startActivity(intent, options.toBundle())
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main_fragments, menu)

        val searchManager = activity?.getSystemService(SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView
        searchView.apply {
            setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
            queryHint = resources.getString(R.string.search_hint)
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    setLoading(true, query)
                    setOnBoardingVisibility(false)

                    searchView.clearFocus()

                    querySearch = query
                    viewModel.requestFindUsers(query)
                    return true
                }
                override fun onQueryTextChange(newText: String): Boolean = false
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setOnBoardingVisibility(state: Boolean) {
        binding.layoutOnboarding.root.visibility = if (state) View.VISIBLE else View.GONE
        binding.layoutOnboarding.lottieAnimationView.visibility = if (state) View.VISIBLE else View.GONE
    }

    private fun setNotFoundVisibility(state: Boolean, query: String = "") {
        binding.layoutNotFound.root.visibility = if (state) View.VISIBLE else View.GONE
        binding.layoutNotFound.lottieAnimationView.isVisible = state
        binding.layoutNotFound.textMessage.visibility = if (state) View.VISIBLE else View.GONE
        binding.layoutNotFound.textMessage.text = getString(R.string.text_not_found, query)
    }

    private fun setLoading(state: Boolean, query: String = "") {
        binding.layoutLoading.root.visibility = if (state) View.VISIBLE else View.GONE
        binding.layoutLoading.lottieAnimationView.visibility = if (state) View.VISIBLE else View.GONE
        binding.layoutLoading.textMessage.visibility = if (state) View.VISIBLE else View.GONE
        binding.layoutLoading.textMessage.text = getString(R.string.find_progress, query)
    }

    private fun setListVisible(state: Boolean) {
        binding.listUsers.isVisible = state
        binding.textResult.isVisible = state
    }

}
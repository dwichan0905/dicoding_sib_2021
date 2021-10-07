package id.dwichan.githubbook.ui.main.home

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import id.dwichan.githubbook.R
import id.dwichan.githubbook.data.network.api.ApiService
import id.dwichan.githubbook.data.network.response.UserItem
import id.dwichan.githubbook.data.network.response.UserSearchResponse
import id.dwichan.githubbook.databinding.FragmentHomeBinding
import id.dwichan.githubbook.databinding.ItemUsersBinding
import id.dwichan.githubbook.ui.detail.DetailActivity
import id.dwichan.githubbook.ui.main.UsersAdapter
import id.dwichan.githubbook.ui.main.favorite.FavoriteFragment
import id.dwichan.githubbook.util.Convert
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.floor

class HomeFragment : Fragment() {

    companion object {
        private const val SEARCH_SERVICE: String = Context.SEARCH_SERVICE
    }

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

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

        setLoading(false)
        setListVisible(false)
        setNotFoundVisibility(false)
        setOnBoardingVisibility(true)
    }

    private fun showUsersList(listItem: List<UserItem>, query: String = "") {
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
                    intent.putExtra(DetailActivity.EXTRA_USER, item)
                    val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        requireActivity(), itemBinding.imageUser, "UserIcon"
                    )
                    startActivity(intent, options.toBundle())
                }
            }

            binding.textResult.text = getString(R.string.text_result, listItem.size, query)
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

                    val imm = requireContext()
                        .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(
                        searchView.applicationWindowToken,
                        InputMethodManager.HIDE_IMPLICIT_ONLY
                    )

                    getUsers(query)
                    return true
                }
                override fun onQueryTextChange(newText: String): Boolean = false
            })
        }
    }

    private fun getUsers(query: String) {
        val client = ApiService.getApiService(requireActivity()).searchUser(query)
        client.enqueue(object: Callback<UserSearchResponse> {
            override fun onResponse(
                call: Call<UserSearchResponse>,
                response: Response<UserSearchResponse>
            ) {
                if (response.isSuccessful) {
                    val responseList = response.body()
                    if (responseList != null) {
                        setLoading(false)
                        setOnBoardingVisibility(false)

                        if (responseList.items.isNotEmpty()) {
                            setListVisible(true)
                            setNotFoundVisibility(false)
                            showUsersList(responseList.items, query)
                        } else {
                            setNotFoundVisibility(true, query)
                            setListVisible(false)
                            showUsersList(ArrayList())
                        }
                    }
                }
            }

            override fun onFailure(call: Call<UserSearchResponse>, t: Throwable) {
                Toast.makeText(
                    requireContext(),
                    "Failure!",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setOnBoardingVisibility(state: Boolean) {
        binding.layoutOnboarding.root.isVisible = state
    }

    private fun setNotFoundVisibility(state: Boolean, query: String = "") {
        binding.layoutNotFound.root.isVisible = state
        binding.layoutNotFound.textMessage.text = getString(R.string.text_not_found, query)
    }

    private fun setLoading(state: Boolean, query: String = "") {
        binding.layoutLoading.root.isVisible = state
        binding.layoutLoading.textMessage.text = getString(R.string.find_progress, query)
    }

    private fun setListVisible(state: Boolean) {
        binding.listUsers.isVisible = state
        binding.textResult.isVisible = state
    }

}
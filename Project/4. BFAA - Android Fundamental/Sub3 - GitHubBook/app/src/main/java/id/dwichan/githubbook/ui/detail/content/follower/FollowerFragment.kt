package id.dwichan.githubbook.ui.detail.content.follower

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import id.dwichan.githubbook.R
import id.dwichan.githubbook.data.network.response.UserItem
import id.dwichan.githubbook.databinding.FragmentRepoFollowListBinding
import id.dwichan.githubbook.databinding.ItemUsersBinding
import id.dwichan.githubbook.ui.detail.DetailActivity
import id.dwichan.githubbook.ui.detail.content.UsersAdapter
import id.dwichan.githubbook.util.Convert
import kotlin.math.floor

class FollowerFragment : Fragment(), UsersAdapter.OnItemActionListener {

    private val viewModel: FollowerViewModel by viewModels()

    private var _binding: FragmentRepoFollowListBinding? = null
    private val binding get() = _binding!!

    private var username: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRepoFollowListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = UsersAdapter()

        arguments?.let {
            username = it.getString(EXTRA_USERNAME).toString()
        }

        viewModel.data.observe(viewLifecycleOwner) { data ->
            if (data.isNotEmpty()) {
                adapter.setUserList(data)
            } else {
                setNotFoundVisibility(true, username)
                adapter.setUserList(ArrayList())
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { state ->
            binding.swipeRefresh.visibility = if (state == true) View.GONE else View.VISIBLE
            setLoading(state, username)
        }

        viewModel.isFailed.observe(viewLifecycleOwner) { state ->
            setNotFoundVisibility(state, username)
        }

        binding.apply {
            rvList.layoutManager = LinearLayoutManager(context)
            val divider =
                MaterialDividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
            divider.dividerInsetStart = floor(Convert.dpToPx(requireContext(), 80)).toInt()
            rvList.addItemDecoration(divider)
            rvList.adapter = adapter
            adapter.onItemAction = this@FollowerFragment
            viewModel.fetchFollower(username)

            swipeRefresh.setOnRefreshListener {
                swipeRefresh.isRefreshing = false
                viewModel.needUpdateData()
                viewModel.fetchFollower(username)
            }
        }
    }


    override fun onClick(item: UserItem, itemBinding: ItemUsersBinding) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_USER_ITEM, item)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            requireActivity(), itemBinding.imageUser, "UserIcon"
        )
        startActivity(intent, options.toBundle())
    }

    private fun setNotFoundVisibility(state: Boolean, name: String = "") {
        binding.layoutNotFound.root.visibility = if (state) View.VISIBLE else View.GONE
        binding.layoutNotFound.lottieEmpty.isVisible = state
        binding.layoutNotFound.textErrorMessage.visibility = if (state) View.VISIBLE else View.GONE
        binding.layoutNotFound.textErrorMessage.text = getString(R.string.no_follower, name)
    }

    private fun setLoading(state: Boolean, query: String = "") {
        binding.layoutLoading.root.visibility = if (state) View.VISIBLE else View.GONE
        binding.layoutLoading.lottieLoading.visibility =
            if (state) View.VISIBLE else View.GONE
        binding.layoutLoading.textLoadingMessage.visibility = if (state) View.VISIBLE else View.GONE
        binding.layoutLoading.textLoadingMessage.text = getString(R.string.find_follower, query)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val EXTRA_USERNAME = "extra_username"

        @JvmStatic
        fun newInstance(username: String) =
            FollowerFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_USERNAME, username)
                }
            }
    }
}
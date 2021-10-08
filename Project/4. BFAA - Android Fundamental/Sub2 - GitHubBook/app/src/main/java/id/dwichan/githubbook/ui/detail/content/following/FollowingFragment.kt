package id.dwichan.githubbook.ui.detail.content.following

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
import id.dwichan.githubbook.util.Convert
import kotlin.math.floor


class FollowingFragment : Fragment(), FollowingAdapter.OnItemActionListener {

    private val viewModel: FollowingViewModel by viewModels()

    private var _binding: FragmentRepoFollowListBinding? = null
    private val binding get() = _binding!!

    private var username: String = ""

    companion object {
        const val EXTRA_USERNAME = "extra_username"

        @JvmStatic
        fun newInstance(username: String) =
            FollowingFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_USERNAME, username)
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRepoFollowListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = FollowingAdapter()

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
            val divider = MaterialDividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
            divider.dividerInsetStart = floor(Convert.dpToPx(requireContext(), 80)).toInt()
            rvList.addItemDecoration(divider)
            rvList.adapter = adapter
            adapter.onItemAction = this@FollowingFragment
            viewModel.fetchFollowing(username)

            swipeRefresh.setOnRefreshListener {
                swipeRefresh.isRefreshing = false
                viewModel.needUpdateData()
                viewModel.fetchFollowing(username)
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
        binding.layoutNotFound.lottieAnimationView.isVisible = state
        binding.layoutNotFound.textMessage.visibility = if (state) View.VISIBLE else View.GONE
        binding.layoutNotFound.textMessage.text = getString(R.string.no_following, name)
    }

    private fun setLoading(state: Boolean, query: String = "") {
        binding.layoutLoading.root.visibility = if (state) View.VISIBLE else View.GONE
        binding.layoutLoading.lottieAnimationView.visibility = if (state) View.VISIBLE else View.GONE
        binding.layoutLoading.textMessage.visibility = if (state) View.VISIBLE else View.GONE
        binding.layoutLoading.textMessage.text = getString(R.string.find_following, query)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
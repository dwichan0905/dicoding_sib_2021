package id.dwichan.githubbook.ui.detail.content.following

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import id.dwichan.githubbook.data.entity.User
import id.dwichan.githubbook.databinding.FragmentRepoFollowListBinding
import id.dwichan.githubbook.databinding.ItemUsersBinding
import id.dwichan.githubbook.ui.detail.DetailActivity
import id.dwichan.githubbook.ui.detail.content.UsersAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class FollowingFragment : Fragment(), UsersAdapter.OnItemActionListener {

    private var _binding: FragmentRepoFollowListBinding? = null
    private val binding get() = _binding!!

    private var username: String = ""
    private var isLoading = false

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

        arguments?.let {
            username = it.getString(EXTRA_USERNAME).toString()
        }

        binding.apply {
            rvList.layoutManager = LinearLayoutManager(context)
            swapLoading()
            val reposAdapter = FollowingAdapter()
            rvList.adapter = reposAdapter
            CoroutineScope(Dispatchers.IO).launch {
                getFollowerList(reposAdapter)
            }
        }
    }

    // FIXME: Bug list nya selalu 0
    private suspend fun getFollowerList(adapter: FollowingAdapter) {

    }

    override fun onClick(item: User, itemBinding: ItemUsersBinding) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_USER_ITEM, item)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            requireActivity(), itemBinding.imageUser, "UserIcon"
        )
        startActivity(intent, options.toBundle())
    }

    private fun swapLoading() {
        if (isLoading) {
            binding.progressBar.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.VISIBLE
        }
        isLoading = !isLoading
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
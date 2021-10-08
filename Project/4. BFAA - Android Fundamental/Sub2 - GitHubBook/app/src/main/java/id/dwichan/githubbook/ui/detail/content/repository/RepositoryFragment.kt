package id.dwichan.githubbook.ui.detail.content.repository

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import id.dwichan.githubbook.R
import id.dwichan.githubbook.databinding.FragmentRepoFollowListBinding


class RepositoryFragment : Fragment() {

    private val viewModel: RepositoryViewModel by viewModels()

    private var _binding: FragmentRepoFollowListBinding? = null
    private val binding get() = _binding!!

    private var username: String = ""

    companion object {
        const val EXTRA_USERNAME = "extra_username"

        @JvmStatic
        fun newInstance(username: String) =
            RepositoryFragment().apply {
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
        val adapter = RepositoryAdapter()

        arguments?.let {
            username = it.getString(EXTRA_USERNAME).toString()
        }

        viewModel.data.observe(viewLifecycleOwner) { data ->
            if (data.isNotEmpty()) {
                adapter.setRepositoryList(data)
            } else {
                setNotFoundVisibility(true, username)
                adapter.setRepositoryList(ArrayList())
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
            rvList.adapter = adapter
            viewModel.fetchRepositories(username)

            swipeRefresh.setOnRefreshListener {
                swipeRefresh.isRefreshing = false
                viewModel.needUpdateData()
                viewModel.fetchRepositories(username)
            }
        }
    }

    private fun setNotFoundVisibility(state: Boolean, name: String = "") {
        binding.layoutNotFound.root.visibility = if (state) View.VISIBLE else View.GONE
        binding.layoutNotFound.lottieEmpty.isVisible = state
        binding.layoutNotFound.textErrorMessage.visibility = if (state) View.VISIBLE else View.GONE
        binding.layoutNotFound.textErrorMessage.text = getString(R.string.no_repository, name)
    }

    private fun setLoading(state: Boolean, name: String = "") {
        binding.layoutLoading.root.visibility = if (state) View.VISIBLE else View.GONE
        binding.layoutLoading.lottieLoading.visibility =
            if (state) View.VISIBLE else View.GONE
        binding.layoutLoading.textLoadingMessage.visibility = if (state) View.VISIBLE else View.GONE
        binding.layoutLoading.textLoadingMessage.text = getString(R.string.find_repository, name)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
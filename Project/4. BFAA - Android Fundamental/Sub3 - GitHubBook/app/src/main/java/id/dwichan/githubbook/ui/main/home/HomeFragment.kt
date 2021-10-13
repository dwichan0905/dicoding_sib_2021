package id.dwichan.githubbook.ui.main.home

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SearchView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.isVisible
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.divider.MaterialDividerItemDecoration
import id.dwichan.githubbook.R
import id.dwichan.githubbook.data.preference.SettingPreferences
import id.dwichan.githubbook.data.preference.SettingPreferencesViewModel
import id.dwichan.githubbook.data.preference.SettingPreferencesViewModelFactory
import id.dwichan.githubbook.data.repository.network.response.UserItem
import id.dwichan.githubbook.databinding.FragmentHomeBinding
import id.dwichan.githubbook.databinding.ItemUsersBinding
import id.dwichan.githubbook.ui.detail.DetailActivity
import id.dwichan.githubbook.util.Convert
import kotlin.math.floor

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var preferenceViewModel: SettingPreferencesViewModel

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var querySearch: String = ""
    private var currentThemeId: Int = -1

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

        val pref = SettingPreferences.getInstance(requireContext().dataStore)
        preferenceViewModel = ViewModelProvider(
            this,
            SettingPreferencesViewModelFactory(pref)
        )[SettingPreferencesViewModel::class.java]

        preferenceViewModel.getTheme().observe(viewLifecycleOwner) { themeId ->
            currentThemeId = themeId
        }
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
        binding.apply {
            val adapter = UsersAdapter()
            val divider =
                MaterialDividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
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
        inflater.inflate(R.menu.menu_main_home_favorites, menu)

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_item_theme) {
            val dialog = MaterialAlertDialogBuilder(requireContext())
                .setTitle(getString(R.string.choose_theme))
                .setSingleChoiceItems(R.array.theme_choices, currentThemeId) { dialog, which ->
                    dialog.dismiss()
                    when (which) {
                        0 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                        1 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    }
                    preferenceViewModel.setTheme(which)
                }
                .create()
            dialog.show()
        }
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setOnBoardingVisibility(state: Boolean) {
        binding.layoutOnboarding.root.visibility = if (state) View.VISIBLE else View.GONE
        binding.layoutOnboarding.lottieOnboarding.visibility =
            if (state) View.VISIBLE else View.GONE
    }

    private fun setNotFoundVisibility(state: Boolean, query: String = "") {
        binding.layoutNotFound.root.visibility = if (state) View.VISIBLE else View.GONE
        binding.layoutNotFound.lottieEmpty.isVisible = state
        binding.layoutNotFound.textErrorMessage.visibility = if (state) View.VISIBLE else View.GONE
        binding.layoutNotFound.textErrorMessage.text = getString(R.string.text_not_found, query)
    }

    private fun setLoading(state: Boolean, query: String = "") {
        binding.layoutLoading.root.visibility = if (state) View.VISIBLE else View.GONE
        binding.layoutLoading.lottieLoading.visibility =
            if (state) View.VISIBLE else View.GONE
        binding.layoutLoading.textLoadingMessage.visibility = if (state) View.VISIBLE else View.GONE
        binding.layoutLoading.textLoadingMessage.text = getString(R.string.find_progress, query)
    }

    private fun setListVisible(state: Boolean) {
        binding.listUsers.isVisible = state
        binding.textResult.isVisible = state
    }

    companion object {
        private const val SEARCH_SERVICE: String = Context.SEARCH_SERVICE
    }

}
package id.dwichan.githubbook.ui.main.favorite

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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.divider.MaterialDividerItemDecoration
import id.dwichan.githubbook.R
import id.dwichan.githubbook.data.preference.SettingPreferences
import id.dwichan.githubbook.data.preference.SettingPreferencesViewModel
import id.dwichan.githubbook.data.preference.SettingPreferencesViewModelFactory
import id.dwichan.githubbook.data.repository.local.entity.FavoriteUser
import id.dwichan.githubbook.data.repository.network.response.UserItem
import id.dwichan.githubbook.databinding.FragmentFavoriteBinding
import id.dwichan.githubbook.databinding.ItemUsersBinding
import id.dwichan.githubbook.ui.detail.DetailActivity
import id.dwichan.githubbook.util.Convert
import kotlin.math.floor

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class FavoriteFragment : Fragment() {

    private val viewModel: FavoriteViewModel by viewModels()
    private lateinit var preferenceViewModel: SettingPreferencesViewModel

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private var currentThemeId: Int = -1

    private val observer = Observer<List<FavoriteUser>> { data ->
        if (data.isNotEmpty()) {
            setListVisible(true)
            setEmptyVisibility(false)
            setListContents(data)
        } else {
            setListVisible(false)
            setEmptyVisibility(true)
            setListContents(ArrayList())
        }
    }

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

        val pref = SettingPreferences.getInstance(requireContext().dataStore)
        preferenceViewModel = ViewModelProvider(
            this,
            SettingPreferencesViewModelFactory(pref)
        )[SettingPreferencesViewModel::class.java]

        preferenceViewModel.getTheme().observe(viewLifecycleOwner) { themeId ->
            currentThemeId = themeId
        }

        viewModel.getUsers().observe(viewLifecycleOwner, observer)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main_home_favorites, menu)

        val searchManager = activity?.getSystemService(SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView
        searchView.apply {
            setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
            queryHint = context.getString(R.string.find_favorite_user)
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    viewModel.getUsers(query).observe(viewLifecycleOwner, observer)
                    return true
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    viewModel.getUsers(newText).observe(viewLifecycleOwner, observer)
                    return true
                }
            })
            setOnCloseListener {
                viewModel.getUsers("").observe(viewLifecycleOwner, observer)
                true
            }
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

    private fun setEmptyVisibility(state: Boolean) {
        binding.layoutEmpty.root.isVisible = state
    }

    private fun setListVisible(state: Boolean) {
        binding.listUsers.isVisible = state
    }

    private fun setListContents(data: List<FavoriteUser>) {
        binding.apply {
            val adapter = FavoriteUsersAdapter()
            val divider =
                MaterialDividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
            divider.dividerInsetStart = floor(Convert.dpToPx(requireContext(), 80)).toInt()
            listUsers.addItemDecoration(divider)
            listUsers.layoutManager = LinearLayoutManager(requireContext())
            listUsers.adapter = adapter
            adapter.setUserList(data)
            adapter.onItemAction = object : FavoriteUsersAdapter.OnItemActionListener {
                override fun onClick(item: FavoriteUser, itemBinding: ItemUsersBinding) {
                    val userItem = UserItem(
                        login = item.login,
                        type = item.type,
                        avatarUrl = item.avatarUrl
                    )
                    val intent = Intent(requireContext(), DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_USER_ITEM, userItem)
                    val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        requireActivity(), itemBinding.imageUser, "UserIcon"
                    )
                    startActivity(intent, options.toBundle())
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val SEARCH_SERVICE: String = Context.SEARCH_SERVICE
    }

}
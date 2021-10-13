package id.dwichan.githubbook.ui.main.about

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.divider.MaterialDividerItemDecoration
import id.dwichan.githubbook.R
import id.dwichan.githubbook.data.entity.Option
import id.dwichan.githubbook.data.preference.SettingPreferences
import id.dwichan.githubbook.data.preference.SettingPreferencesViewModel
import id.dwichan.githubbook.data.preference.SettingPreferencesViewModelFactory
import id.dwichan.githubbook.databinding.FragmentAboutBinding
import id.dwichan.githubbook.ui.options.OptionsAdapter

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class AboutFragment : Fragment() {

    private lateinit var preferenceViewModel: SettingPreferencesViewModel

    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    private var currentThemeId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
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

        val options: List<Option> = listOf(
            Option(
                icon = R.drawable.ic_baseline_facebook_24,
                title = "Visit me on Facebook",
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("https://web.facebook.com/CdrScNET89")
                    startActivity(intent)
                }
            ),
            Option(
                icon = R.drawable.github_mark,
                title = "Visit me on GitHub",
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("https://github.com/dwichan0905")
                    startActivity(intent)
                }
            ),
            Option(
                icon = R.drawable.ic_baseline_email_24,
                title = "Send me an email",
                onClick = {
                    val intent = Intent(Intent.ACTION_SENDTO)
                    intent.data = Uri.parse("mailto:dwichan@outlook.com")
                    if (intent.resolveActivity(requireActivity().packageManager) != null) {
                        startActivity(intent)
                    }
                }
            )
        )
        binding.listOptions.layoutManager = LinearLayoutManager(requireContext())
        val divider = MaterialDividerItemDecoration(
            requireContext(), MaterialDividerItemDecoration.VERTICAL
        )
        binding.listOptions.addItemDecoration(divider)
        binding.listOptions.adapter = OptionsAdapter(options)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_about, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_item_theme) {
            val dialog = MaterialAlertDialogBuilder(requireContext())
                .setTitle("Choose a theme")
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
}
package id.dwichan.githubbook.ui.main.about

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import id.dwichan.githubbook.R
import id.dwichan.githubbook.data.entity.Option
import id.dwichan.githubbook.databinding.FragmentAboutBinding
import id.dwichan.githubbook.ui.options.OptionsAdapter

class AboutFragment : Fragment() {

    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
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
        setHasOptionsMenu(false)

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package id.dwichan.moviedicts.ui.main.about

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import id.dwichan.moviedicts.R
import id.dwichan.moviedicts.core.data.entity.OptionDataEntity
import id.dwichan.moviedicts.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {

    // fix memory leak
    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val optionDataEntities: List<OptionDataEntity> = listOf(
            OptionDataEntity(
                icon = R.drawable.ic_baseline_facebook_24,
                title = getString(R.string.visit_me_facebook),
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("https://web.facebook.com/CdrScNET89")
                    startActivity(intent)
                }
            ),
            OptionDataEntity(
                icon = R.drawable.github_mark,
                title = getString(R.string.visit_me_github),
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("https://github.com/dwichan0905")
                    startActivity(intent)
                }
            ),
            OptionDataEntity(
                icon = R.drawable.ic_baseline_email_24,
                title = getString(R.string.send_me_email),
                onClick = {
                    val intent = Intent(Intent.ACTION_SENDTO)
                    intent.data = Uri.parse("mailto:dwichan@outlook.com")
                    startActivity(intent)
                }
            )
        )
        binding?.listOptions?.layoutManager = LinearLayoutManager(requireContext())
        val divider = MaterialDividerItemDecoration(
            requireContext(), MaterialDividerItemDecoration.VERTICAL
        )
        binding?.listOptions?.addItemDecoration(divider)
        binding?.listOptions?.adapter = OptionsAdapter(optionDataEntities)
    }

    // fix memory leak
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
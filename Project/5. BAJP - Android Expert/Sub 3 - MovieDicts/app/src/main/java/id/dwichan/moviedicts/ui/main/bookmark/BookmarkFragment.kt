package id.dwichan.moviedicts.ui.main.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import id.dwichan.moviedicts.databinding.FragmentBookmarkBinding

class BookmarkFragment : Fragment() {

    private var _binding: FragmentBookmarkBinding? = null
    private val binding get() = _binding
    private var tabLayoutMediator: TabLayoutMediator? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            if (this != null) {
                val pagerAdapter = BookmarkPagerAdapter(requireActivity() as AppCompatActivity)
                viewPager.adapter = pagerAdapter
                tabLayoutMediator = TabLayoutMediator(tabs, viewPager) { tab, position ->
                    tab.text = resources.getString(pagerAdapter.titles[position])
                }
                tabLayoutMediator?.attach()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.viewPager?.adapter = null
        _binding = null
        if (tabLayoutMediator?.isAttached!!) tabLayoutMediator?.detach()
        tabLayoutMediator = null
    }

}
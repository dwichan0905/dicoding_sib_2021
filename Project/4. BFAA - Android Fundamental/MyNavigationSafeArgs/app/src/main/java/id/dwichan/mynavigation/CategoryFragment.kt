package id.dwichan.mynavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import id.dwichan.mynavigation.databinding.FragmentCategoryBinding

class CategoryFragment : Fragment() {

    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonCategoryLifestyle.setOnClickListener {
            val toDetailCategoryFragment = CategoryFragmentDirections
                .actionCategoryFragmentToCategoryLifestyleFragment().apply {
                    name = "Lifestyle"
                    stock = 7L
                }
            view.findNavController().navigate(toDetailCategoryFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
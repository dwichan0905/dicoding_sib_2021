package id.dwichan.mynavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.dwichan.mynavigation.databinding.FragmentCategoryLifestyleBinding

class CategoryLifestyleFragment : Fragment() {

    private var _binding: FragmentCategoryLifestyleBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_STOCK = "extra_stock"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryLifestyleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataName = CategoryLifestyleFragmentArgs.fromBundle(arguments as Bundle).name
        val dataDescription = """
            Stock: ${ CategoryLifestyleFragmentArgs.fromBundle(arguments as Bundle).stock } item(s)
        """.trimIndent()

        binding.apply {
            textCategoryName.text = dataName
            textCategoryDescription.text = dataDescription
        }
    }

}
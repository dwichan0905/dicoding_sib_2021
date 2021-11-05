package id.dwichan.moviedicts.ui.main.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import id.dwichan.moviedicts.R
import id.dwichan.moviedicts.databinding.FragmentMoviesBinding

class MoviesFragment : Fragment() {

    private val viewModel: TrendingMoviesViewModel by viewModels()

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding =  FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.isLoading.observe(viewLifecycleOwner) { state ->

        }

        viewModel.trendingToday.observe(viewLifecycleOwner) { data ->

        }

        viewModel.trendingWeekly.observe(viewLifecycleOwner) { data ->

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
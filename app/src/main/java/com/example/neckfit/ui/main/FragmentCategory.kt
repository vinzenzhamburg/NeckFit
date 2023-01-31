package com.example.neckfit.ui.main


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.neckfit.adapter.CategoryAdapter
import com.example.neckfit.databinding.FragmentCategoryBinding
import com.example.neckfit.ui.ApiStatus
import com.example.neckfit.ui.MainViewModel


class FragmentCategory : Fragment() {

    private lateinit var binding: FragmentCategoryBinding
    private val categoryAdapter: CategoryAdapter by lazy { CategoryAdapter() }
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val themeTitle = requireArguments().getString("themeTitle")
        viewModel.themes.observe(viewLifecycleOwner) {
            val clickedTheme = it.find {
                it.name == themeTitle
            }

            if (clickedTheme != null) {
                val types = clickedTheme.types
                viewModel.setTypes(types)
                categoryAdapter.submitList(types)
            }

            binding.homeRecycler.adapter = categoryAdapter
            binding.backButtonCategory.setOnClickListener {
                findNavController().navigateUp()
            }
        }
        viewModel.loading.observe(viewLifecycleOwner) {
            when (it) {
                ApiStatus.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                ApiStatus.DONE -> {
                    binding.progressBar.visibility = View.GONE
                }
                ApiStatus.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                }
            }
        }
    }
}
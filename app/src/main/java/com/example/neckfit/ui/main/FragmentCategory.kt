package com.example.neckfit.ui.main


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.neckfit.R
import com.example.neckfit.adapter.CategoryAdapter
import com.example.neckfit.adapter.ThemeAdapter
import com.example.neckfit.databinding.FragmentCategoryBinding
import com.example.neckfit.databinding.FragmentLoginBinding
import com.example.neckfit.databinding.FragmentThemeBinding
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

        binding.homeRecycler.adapter= categoryAdapter

        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}
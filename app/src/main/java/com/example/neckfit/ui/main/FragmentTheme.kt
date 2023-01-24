package com.example.neckfit.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.neckfit.adapter.ThemeAdapter
import com.example.neckfit.databinding.FragmentThemeBinding
import com.example.neckfit.ui.MainViewModel


class FragmentTheme : Fragment() {

    private lateinit var binding: FragmentThemeBinding


    private val viewModel: MainViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentThemeBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val themeAdapter = ThemeAdapter()

        binding.themeRecycler.adapter= themeAdapter

        binding.backButtonTraining.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.themeRecycler.adapter = themeAdapter
        viewModel.getThemes()

        viewModel.themes.observe(viewLifecycleOwner){
            themeAdapter.submitList(it)
        }
        binding.allButton.setOnClickListener {
        //TODO Button hinzufügen.
            findNavController()
                .navigate(FragmentThemeDirections.actionFragmentThemeToFragmentTraining())
        }

    }
}
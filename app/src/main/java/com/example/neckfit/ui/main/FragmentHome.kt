package com.example.neckfit.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.neckfit.adapter.ThemeAdapter
import com.example.neckfit.databinding.FragmentHomeBinding
import com.example.neckfit.ui.MainViewModel


class FragmentHome : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: MainViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.startButton.setOnClickListener {
            findNavController().navigate(FragmentHomeDirections.actionFragmentHomeToFragmentTheme())
        }
        binding.logoutButton.setOnClickListener {
            viewModel.logout()
            findNavController().navigate(FragmentHomeDirections.actionFragmentHomeToLoginFragment())
        }
    }
}
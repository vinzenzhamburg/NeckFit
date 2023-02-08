package com.example.neckfit.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.neckfit.adapter.AllTrainAdapter
import com.example.neckfit.adapter.FavoriteAdapter
import com.example.neckfit.databinding.FragmentAllBinding
import com.example.neckfit.databinding.FragmentFavoriteBinding
import com.example.neckfit.ui.MainViewModel

class FavoriteFragment : Fragment() {


    private lateinit var binding: FragmentFavoriteBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backButtonTraining.setOnClickListener {
            findNavController().navigateUp()
        }
        val allAdapter = AllTrainAdapter(viewModel)


        binding.favoriteTrainingsRecycler.adapter = allAdapter

        viewModel.favoriteTraining.observe(viewLifecycleOwner) {
            allAdapter.submitList(it)
        }

        viewModel.favoriteTraining.observe(viewLifecycleOwner) {
            allAdapter.favoriteList(it)
        }

        var snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.favoriteTrainingsRecycler)
    }
}














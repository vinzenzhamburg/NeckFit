package com.example.neckfit.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.neckfit.R
import com.example.neckfit.adapter.AllAdapter
import com.example.neckfit.adapter.AllTrainAdapter
import com.example.neckfit.databinding.FragmentAllBinding
import com.example.neckfit.databinding.FragmentTrainingBinding
import com.example.neckfit.ui.MainViewModel


class AllFragment : Fragment() {

    private lateinit var binding: FragmentAllBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backButtonTraining.setOnClickListener {
            findNavController().navigateUp()
        }
        val allAdapter = AllAdapter()

        viewModel.getAllTraining()
        binding.allTrainingsRecycler.adapter = allAdapter

        viewModel.allTraining.observe(viewLifecycleOwner){
            allAdapter.submitList(it)
        }

        var snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.allTrainingsRecycler)
    }
}
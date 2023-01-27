package com.example.neckfit.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.neckfit.R
import com.example.neckfit.adapter.AllTrainAdapter
import com.example.neckfit.data.datamodel.Training
import com.example.neckfit.databinding.FragmentCategoryBinding
import com.example.neckfit.databinding.FragmentTrainingBinding
import com.example.neckfit.ui.MainViewModel

class FragmentTraining : Fragment() {

    private lateinit var binding: FragmentTrainingBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTrainingBinding.inflate(inflater)

        return binding.root
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backButtonTraining.setOnClickListener {
            findNavController().navigateUp()
        }
        val trainingsAdapter = AllTrainAdapter()

        viewModel.getAllTraining()
        val category = requireArguments().getString("category")
            if (category != null || category != ".")
            {viewModel. .observe(viewLifecycleOwner){
            trainingsAdapter.submitList(it)}
            } else {
            viewModel.allTraining.observe(viewLifecycleOwner)
            { trainingsAdapter.submitList(it) }
        }
        binding.allTrainingsRecycler.adapter = trainingsAdapter

        var snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.allTrainingsRecycler)
    }

}
package com.example.neckfit.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.neckfit.adapter.AllTrainAdapter
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryName = requireArguments().getString("category")

        binding.backButtonTraining.setOnClickListener {
            findNavController().navigateUp()
        }
        val trainingsAdapter = AllTrainAdapter()

        viewModel.getAllTraining()
        binding.allTrainingsRecycler.adapter = trainingsAdapter

        viewModel.allTraining.observe(viewLifecycleOwner) { allTraining ->

            viewModel.types.observe(viewLifecycleOwner) { list ->
                val category = list.find { it.name == categoryName }

                val trainings = viewModel.allTraining.value?.filter {
                    category?.exercises?.contains(it.id) == true
                }
                if (trainings != null) {
                    trainingsAdapter.submitList(trainings)
                }
            }
        }

        var snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.allTrainingsRecycler)
    }
}

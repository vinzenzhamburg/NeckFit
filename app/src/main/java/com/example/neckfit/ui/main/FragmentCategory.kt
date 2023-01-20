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

        // 1. in themeTitle den Namen der Kategorie speichern, auf die wir geklickt haben
        val themeTitle = requireArguments().getString("themeTitle")
        // 2. aus der themes liste raussuchen, welches davon mit dem angeklickten übereinstimmt
        viewModel.themes.observe(viewLifecycleOwner) {
            // 3. in clickedTheme das Element in der themes Liste speichern, das den richtigen Namen hat.
            // Dieses Element ist vom Typ Theme, bedeutet: es hat einen namen und eine liste an typen.
            val clickedTheme = it.find {
                it.name == themeTitle
            }
            // element gefunden, dessen typen mit clickedTheme.types in der Liste exercises speichern.
            if (clickedTheme != null) {
                val exercises = clickedTheme.types
                // jetzt koennen wir easy mit submitList aus dem adapter diese exercises in die RV laden.
                categoryAdapter.submitList(exercises)
            }


            binding.homeRecycler.adapter = categoryAdapter



            binding.backButton.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }
}
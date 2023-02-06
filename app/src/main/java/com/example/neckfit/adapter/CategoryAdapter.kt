package com.example.neckfit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.neckfit.R
import com.example.neckfit.data.datamodel.Category
import com.example.neckfit.ui.main.FragmentCategoryDirections


class CategoryAdapter
    : RecyclerView.Adapter<CategoryAdapter.ItemViewHolder>() {
    private var dataset: List<Category> = emptyList()

    fun submitList(list: List<Category>) {
        dataset = list
        notifyDataSetChanged()
    }

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        val name: TextView = view.findViewById(R.id.category_title_name)
        val categoryButton: View = view.findViewById(R.id.category_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_category, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val exercise = dataset[position]

        holder.name.text = exercise.name
        holder.categoryButton.setOnClickListener {
            holder.itemView.findNavController()
                .navigate(
                    FragmentCategoryDirections.actionFragmentCategoryToFragmentTraining(
                        category = exercise.name
                    )
                )
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}
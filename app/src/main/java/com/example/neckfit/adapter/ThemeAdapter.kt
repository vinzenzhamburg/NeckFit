package com.example.neckfit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.neckfit.R
import com.example.neckfit.data.datamodel.Theme
import com.example.neckfit.ui.main.FragmentThemeDirections

class ThemeAdapter() : RecyclerView.Adapter<ThemeAdapter.ItemViewHolder>() {

    private var dataset: List<Theme> = emptyList()

    fun submitList(list: List<Theme>) {
        dataset = list
        notifyDataSetChanged()
    }

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        val name: TextView = view.findViewById(R.id.category_title_name)
        val themeButton: ConstraintLayout = view.findViewById(R.id.themen_item_Constrain)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_themen, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val theme = dataset[position]

        holder.name.text = theme.name
        holder.themeButton.setOnClickListener {
            holder.itemView.findNavController()
            .navigate(FragmentThemeDirections.actionFragmentThemeToFragmentCategory(theme.name))
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}
package com.example.neckfit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.neckfit.R
import com.example.neckfit.data.datamodel.Type
import com.example.neckfit.data.datamodel.Uebung
import com.example.neckfit.ui.main.FragmentCategoryDirections
import com.example.neckfit.ui.main.FragmentThemeDirections


class CategoryAdapter(

    ) : RecyclerView.Adapter<CategoryAdapter.ItemViewHolder>() {
    private var dataset: List<Type> = emptyList()

    fun submitList(list : List<Type>){
        dataset = list
        notifyDataSetChanged()
    }

    // IDEE EINES VIEWHOLDERS
    // der ViewHolder weiß welche Teile des Layouts beim Recycling angepasst werden
    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.category_title_name)

        val categoryButton: View =  view.findViewById(R.id.category_button)
    }

    // ERSTELLEN DES VIEWHOLDERS
    // hier werden neue ViewHolder erstellt
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_category, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    // BEFÜLLEN DES VIEWHOLDERS
    // hier findet der Recyclingprozess statt
    // die vom ViewHolder bereitgestellten Parameter werden verändert
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val exercise = dataset[position]
        // wie immer den String, der in exercise.name steht, in die View laden
        holder.name.text = exercise.name

        holder.categoryButton.setOnClickListener {
            holder.itemView.findNavController()
                .navigate(FragmentCategoryDirections.actionFragmentCategoryToFragmentTraining(category = holder.name.text as String))
        }

    }

    // damit der LayoutManager weiß wie lang die Liste ist
    override fun getItemCount(): Int {
        return dataset.size
    }
}

package com.example.neckfit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.neckfit.R
import com.example.neckfit.data.datamodel.Theme
import com.example.neckfit.data.datamodel.Training
import com.example.neckfit.ui.main.FragmentThemeDirections

class AllTrainAdapter (
) : RecyclerView.Adapter<AllTrainAdapter.ItemViewHolder>() {
    private var dataset: List<Training> = emptyList()

    fun submitList(list : List<Training>){
        dataset = list
        notifyDataSetChanged()
    }

    // IDEE EINES VIEWHOLDERS
    // der ViewHolder weiß welche Teile des Layouts beim Recycling angepasst werden
    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val description: TextView = view.findViewById(R.id.text_alltrain)
        val image: ImageView = view.findViewById(R.id.imageAllTrain)
        val backButtonTraining: Button = view.findViewById(R.id.backButtonTraining)
    }

    // ERSTELLEN DES VIEWHOLDERS
    // hier werden neue ViewHolder erstellt
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_all_training, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    // BEFÜLLEN DES VIEWHOLDERS
    // hier findet der Recyclingprozess statt
    // die vom ViewHolder bereitgestellten Parameter werden verändert
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val training = dataset[position]
//TODO: ALLBUTTON Hinzufügen
        holder.image.setImageResource(training.image)
        holder.description.text = training.description
        holder.backButtonTraining.setOnClickListener {
            holder.itemView.findNavController()
                .navigate(FragmentThemeDirections.actionFragmentThemeToFragmentTraining())
        }
    }

    // damit der LayoutManager weiß wie lang die Liste ist
    override fun getItemCount(): Int {
        return dataset.size
    }
}
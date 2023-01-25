package com.example.neckfit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.neckfit.R
import com.example.neckfit.data.datamodel.Training

class AllTrainAdapter ()
    : RecyclerView.Adapter<AllTrainAdapter.ItemViewHolder>() {
    private var dataset: List<Training> = emptyList()
    fun submitList(list : List<Training>){
        dataset = list
        notifyDataSetChanged()
    }

    // der ViewHolder weiß welche Teile des Layouts beim Recycling angepasst werden
    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        val image: ImageView = view.findViewById(R.id.imageAllTrain)
        val description: TextView = view.findViewById(R.id.text_alltrain)
    }

    // ERSTELLEN DES VIEWHOLDERS
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_all_training, parent, false)
        return ItemViewHolder(adapterLayout)


    }

    // BEFÜLLEN DES VIEWHOLDERS
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val training = dataset[position]

        holder.image.load(training.image)
        holder.description.text = training.description
    }

    // damit der LayoutManager weiß wie lang die Liste ist
    override fun getItemCount(): Int {
        return dataset.size
    }
}
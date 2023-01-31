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

class AllAdapter : RecyclerView.Adapter<AllAdapter.ItemViewHolder>() {

    private var dataset: List<Training> = emptyList()


    fun submitList(allTraining: List<Training>) {
        dataset = allTraining
        notifyDataSetChanged()
    }
    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        val image: ImageView = view.findViewById(R.id.imageAllTrain)
        val description: TextView = view.findViewById(R.id.text_alltrain)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllAdapter.ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_all_training, parent, false)
        return AllAdapter.ItemViewHolder(adapterLayout)

    }



    // damit der LayoutManager weiß wie lang die Liste ist
    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val training = dataset[position]

        holder.image.load(training.image)
        holder.description.text = training.description
    }
}

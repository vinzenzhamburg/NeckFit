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

class FavoriteAdapter: RecyclerView.Adapter<FavoriteAdapter.ItemViewHolder>(){

        private var dataset: List<Training> = emptyList()

    fun favoriteList(list: List<Training>){
            dataset = list
            notifyDataSetChanged()
        }

    class ItemViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

        val imageFavorite: ImageView = view.findViewById(R.id.imageAllTrain)
        val textFavorite: TextView = view.findViewById(R.id.text_alltrain)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_all_training, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val training = dataset[position]

        holder.imageFavorite.load(training.image)
        holder.textFavorite.text = training.description

    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}
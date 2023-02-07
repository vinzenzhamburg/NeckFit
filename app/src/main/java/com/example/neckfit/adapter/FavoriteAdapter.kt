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
import com.example.neckfit.ui.MainViewModel

class FavoriteAdapter(private val mainViewModel : MainViewModel)
    : RecyclerView.Adapter<FavoriteAdapter.ItemViewHolder>(){

        private var dataset: List<Training> = emptyList()
        fun submitList(list : List<Training>){
            dataset = list
            notifyDataSetChanged()
        }

        private var favoriteList: List<Training> = emptyList()
        fun favoriteList(list: List<Training>){
            //dataset = list
            favoriteList = list
            notifyDataSetChanged()
//TODO : dapater zu laufen bringen

        }

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        val imageFavorite: ImageView = view.findViewById(R.id.imageAllTrain)
        val textFavorite: TextView = view.findViewById(R.id.text_alltrain)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
    : FavoriteAdapter.ItemViewHolder {

        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_all_training, parent, false)

        return FavoriteAdapter.ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: FavoriteAdapter.ItemViewHolder, position: Int) {

        val training = dataset[position]

        holder.imageFavorite.load(training.favorite)
        holder.textFavorite.text = training.description

    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}
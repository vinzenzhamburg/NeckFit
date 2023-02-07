package com.example.neckfit.adapter

import android.util.Log
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

class AllTrainAdapter(private val mainViewModel: MainViewModel) :
    RecyclerView.Adapter<AllTrainAdapter.ItemViewHolder>() {

    private var dataset: List<Training> = emptyList()

    fun submitList(list: List<Training>) {
        dataset = list
        notifyDataSetChanged()
    }

    private var favoriteList: List<Training> = emptyList()
    fun favoriteList(list: List<Training>) {
        favoriteList = list
        notifyDataSetChanged()
        Log.d("favoriteListtest", favoriteList.size.toString())

    }

    private fun checkFavorite(training: Training): Boolean {
        val isFavorite = favoriteList.filter {
            it.id == training.id
        }.isNotEmpty()

        Log.d("favoriteListtest", isFavorite.toString())
        return isFavorite
    }

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        val image: ImageView = view.findViewById(R.id.imageAllTrain)
        val description: TextView = view.findViewById(R.id.text_alltrain)
        val favoriteStar: ImageView = view.findViewById(R.id.favoriteStar)
        val favoriteStarRed: ImageView = view.findViewById(R.id.favoriteStarRed)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_all_training, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val training = dataset[position]

        holder.image.load(training.image)
        holder.description.text = training.description

        if (checkFavorite(training)) {

            holder.favoriteStarRed.setOnClickListener {
                mainViewModel.deleteFavorite(training)
            }

            holder.favoriteStarRed.visibility = View.VISIBLE
            holder.favoriteStar.visibility = View.INVISIBLE

        } else {

            holder.favoriteStar.setOnClickListener {
                mainViewModel.setFavorite(training)
            }

            holder.favoriteStarRed.visibility = View.INVISIBLE
            holder.favoriteStar.visibility = View.VISIBLE

        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}
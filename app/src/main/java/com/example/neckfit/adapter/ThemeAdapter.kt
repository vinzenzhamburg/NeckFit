package com.example.neckfit.adapter



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.neckfit.R
import com.example.neckfit.data.datamodel.Theme

class ThemeAdapter(
    private val dataset: List<Theme>
) : RecyclerView.Adapter<ThemeAdapter.ItemViewHolder>() {

    // IDEE EINES VIEWHOLDERS
    // der ViewHolder weiß welche Teile des Layouts beim Recycling angepasst werden
    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.title_themen)
        //val teamRecycler: RecyclerView = view.findViewById(R.id.sport_team_recycler)
    }

    // ERSTELLEN DES VIEWHOLDERS
    // hier werden neue ViewHolder erstellt
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_themen, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    // BEFÜLLEN DES VIEWHOLDERS
    // hier findet der Recyclingprozess statt
    // die vom ViewHolder bereitgestellten Parameter werden verändert
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val theme = dataset[position]

        holder.name.text = theme.name
        //holder.teamRecycler.adapter = TrainingAdapter(sport.teams)
        //holder.teamRecycler.setHasFixedSize(true)
    }

    // damit der LayoutManager weiß wie lang die Liste ist
    override fun getItemCount(): Int {
        return dataset.size
    }
}
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

class ThemeAdapter(
    ) : RecyclerView.Adapter<ThemeAdapter.ItemViewHolder>() {
    private var dataset: List<Theme> = emptyList()

    fun submitList(list : List<Theme>){
        dataset = list
        notifyDataSetChanged()
    }

    // IDEE EINES VIEWHOLDERS
    // der ViewHolder weiß welche Teile des Layouts beim Recycling angepasst werden
    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.category_title_name)
        val themeButton: ConstraintLayout = view.findViewById(R.id.themen_item_Constrain)
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
//TODO: ALLBUTTON Hinzufügen
        holder.themeButton.setOnClickListener {
            holder.itemView.findNavController()
                .navigate(FragmentThemeDirections.actionFragmentThemeToFragmentCategory(theme.name))
        }
        holder.name.text = theme.name
    }

    // damit der LayoutManager weiß wie lang die Liste ist
    override fun getItemCount(): Int {
        return dataset.size
    }
}
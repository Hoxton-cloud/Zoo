package ru.zoo.presentation.tables.specieses.listDirectory.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import ru.zoo.R
import ru.zoo.data.models.Species

class SpeciesesAdapter (
    private val specieses: ArrayList<Species>,
    private val checkedSpecies: ArrayList<Species>,
    val context: Context,
    var onClick : ((erection: Species) -> Unit)
) :
    RecyclerView.Adapter<SpeciesesHolder>() {
    var view: View? = null
    override fun onBindViewHolder(holder: SpeciesesHolder, position: Int) {
        val item = specieses[position]
        val lastItem = position == specieses.lastIndex
        holder.bindItem(item,checkedSpecies,onClick,lastItem)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeciesesHolder {
        val inflatedView = parent.inflate(R.layout.list_item_simple_card, false)
        view = inflatedView


        return SpeciesesHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return specieses.size
    }

    fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }
}
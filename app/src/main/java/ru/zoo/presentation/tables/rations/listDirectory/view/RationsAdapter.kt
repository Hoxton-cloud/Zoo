package ru.zoo.presentation.tables.rations.listDirectory.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import ru.zoo.R
import ru.zoo.data.models.Ration

class RationsAdapter (
    private val rations: ArrayList<Ration>,
    private val checkedRation: ArrayList<Ration>,
    val context: Context,
    var onClick : ((erection: Ration) -> Unit)
) :
    RecyclerView.Adapter<RationsHolder>() {
    var view: View? = null
    override fun onBindViewHolder(holder: RationsHolder, position: Int) {
        val item = rations[position]
        val lastItem = position == rations.lastIndex
        holder.bindItem(item,checkedRation,onClick,lastItem)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RationsHolder {
        val inflatedView = parent.inflate(R.layout.list_item_simple_card, false)
        view = inflatedView


        return RationsHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return rations.size
    }

    fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }
}
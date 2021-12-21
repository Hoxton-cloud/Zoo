package ru.zoo.presentation.tables.animals.listDirectory.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import ru.zoo.R
import ru.zoo.data.models.Animal

class AnimalsAdapter (
    private val animals: ArrayList<Animal>,
    private val checkedAnimal: ArrayList<Animal>,
    val context: Context,
    var onClick : ((erection: Animal) -> Unit)
) :
    RecyclerView.Adapter<AnimalsHolder>() {
    var view: View? = null
    override fun onBindViewHolder(holder: AnimalsHolder, position: Int) {
        val item = animals[position]
        val lastItem = position == animals.lastIndex
        holder.bindItem(item,checkedAnimal,onClick,lastItem)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalsHolder {
        val inflatedView = parent.inflate(R.layout.list_item_simple_card, false)
        view = inflatedView


        return AnimalsHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return animals.size
    }

    fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }
}
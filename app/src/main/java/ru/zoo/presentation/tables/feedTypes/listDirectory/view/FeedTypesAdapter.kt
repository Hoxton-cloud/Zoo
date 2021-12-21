package ru.zoo.presentation.tables.feedTypes.listDirectory.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import ru.zoo.R
import ru.zoo.data.models.FeedType

class FeedTypesAdapter (
    private val feedTypes: ArrayList<FeedType>,
    private val checkedFeedType: ArrayList<FeedType>,
    val context: Context,
    var onClick : ((erection: FeedType) -> Unit)
) :
    RecyclerView.Adapter<FeedTypesHolder>() {
    var view: View? = null
    override fun onBindViewHolder(holder: FeedTypesHolder, position: Int) {
        val item = feedTypes[position]
        val lastItem = position == feedTypes.lastIndex
        holder.bindItem(item,checkedFeedType,onClick,lastItem)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedTypesHolder {
        val inflatedView = parent.inflate(R.layout.list_item_simple_card, false)
        view = inflatedView


        return FeedTypesHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return feedTypes.size
    }

    fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }
}
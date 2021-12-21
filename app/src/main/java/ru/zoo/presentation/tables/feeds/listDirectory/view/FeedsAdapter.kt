package ru.zoo.presentation.tables.feeds.listDirectory.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import ru.zoo.R
import ru.zoo.data.models.Feed

class FeedsAdapter (
    private val feeds: ArrayList<Feed>,
    private val checkedFeed: ArrayList<Feed>,
    val context: Context,
    var onClick : ((erection: Feed) -> Unit)
) :
    RecyclerView.Adapter<FeedsHolder>() {
    var view: View? = null
    override fun onBindViewHolder(holder: FeedsHolder, position: Int) {
        val item = feeds[position]
        val lastItem = position == feeds.lastIndex
        holder.bindItem(item,checkedFeed,onClick,lastItem)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedsHolder {
        val inflatedView = parent.inflate(R.layout.list_item_simple_card, false)
        view = inflatedView


        return FeedsHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return feeds.size
    }

    fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }
}
package ru.zoo.presentation.tables.users.listDirectory.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import ru.zoo.R
import ru.zoo.data.models.User

class UsersAdapter (
    private val users: ArrayList<User>,
    private val checkedUser: ArrayList<User>,
    val context: Context,
    var onClick : ((erection: User) -> Unit)
) :
    RecyclerView.Adapter<UsersHolder>() {
    var view: View? = null
    override fun onBindViewHolder(holder: UsersHolder, position: Int) {
        val item = users[position]
        val lastItem = position == users.lastIndex
        holder.bindItem(item,checkedUser,onClick,lastItem)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersHolder {
        val inflatedView = parent.inflate(R.layout.list_item_simple_card, false)
        view = inflatedView


        return UsersHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }
}
package ru.zoo.presentation.tables.users.listDirectory

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_users.view.*
import ru.zoo.data.models.User
import ru.zoo.extensions.view.IProgressView
import ru.zoo.extensions.view.gone
import ru.zoo.extensions.view.visible
import ru.zoo.presentation.tables.users.listDirectory.UsersRepository.Companion.addUser
import ru.zoo.presentation.tables.users.listDirectory.UsersRepository.Companion.checkedUser
import ru.zoo.presentation.tables.users.listDirectory.UsersRepository.Companion.users
import ru.zoo.presentation.tables.users.listDirectory.view.UsersAdapter

class UsersPresenter (
    val activity: Activity,
    val context: Context,
    val layout: ConstraintLayout
) : IProgressView {
    val toolbar = layout.include_toolbar
    val progressView = layout.progress_view
    val recyclerView = layout.list_view_users

    val db = UsersDB(context, this, activity)

    override fun hideLoading() {
        progressView.gone()
    }

    override fun showLoading() {
        progressView.visible()
    }

    fun setListView() {
        val onClick: (user: User) -> Unit = { user->
            if (!checkedUser.any { it.id == user.id }) {
                checkedUser.clear()
            }
            addUser(user)
            recyclerView!!.adapter!!.notifyDataSetChanged()
            val intent = Intent()
            activity.setResult(AppCompatActivity.RESULT_OK, intent)
            activity.finish()
        }
        recyclerView.adapter =
            UsersAdapter(users, checkedUser, context, onClick)
    }
}
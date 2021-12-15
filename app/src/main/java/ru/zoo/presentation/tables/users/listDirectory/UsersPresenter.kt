package ru.zoo.presentation.tables.users.listDirectory

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_users.view.*
import ru.zoo.data.Constants.REQUEST_CODE_USERS_DIRECTORY
import ru.zoo.data.Constants.REQUEST_CODE_USERS_EDIT
import ru.zoo.data.Constants.REQUEST_CODE_USERS_LIST
import ru.zoo.data.models.User
import ru.zoo.extensions.view.IProgressView
import ru.zoo.extensions.view.gone
import ru.zoo.extensions.view.visible
import ru.zoo.presentation.tables.users.createEdit.UsersEditActivity
import ru.zoo.presentation.tables.users.listDirectory.UsersRepository.Companion.addUser
import ru.zoo.presentation.tables.users.listDirectory.UsersRepository.Companion.checkedUser
import ru.zoo.presentation.tables.users.listDirectory.UsersRepository.Companion.redUsers
import ru.zoo.presentation.tables.users.listDirectory.UsersRepository.Companion.requestCode
import ru.zoo.presentation.tables.users.listDirectory.UsersRepository.Companion.users
import ru.zoo.presentation.tables.users.listDirectory.view.UsersAdapter
import java.util.*

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

    fun getUsers(){
        db.getUsers()
    }

    fun search(s: String) {
        redUsers.clear()
        users.forEach {
            if (it.username.toLowerCase(Locale.getDefault()).contains(s)) {
                redUsers.add(it)
            }
        }
        setListView()
    }

    fun setListView() {
        val onClick: (user: User) -> Unit = { user->
            if(requestCode == REQUEST_CODE_USERS_LIST){
                UsersEditActivity.startForResultEdit(
                    activity,
                    REQUEST_CODE_USERS_EDIT,
                    user
                )
            }else if (requestCode == REQUEST_CODE_USERS_DIRECTORY) {
                if (!checkedUser.any { it.id == user.id }) {
                    checkedUser.clear()
                }
                addUser(user)
                recyclerView!!.adapter!!.notifyDataSetChanged()
                val intent = Intent()
                activity.setResult(AppCompatActivity.RESULT_OK, intent)
                activity.finish()
            }
        }
        recyclerView.adapter =
            UsersAdapter(redUsers, checkedUser, context, onClick)
    }
}
package ru.zoo.presentation.tables.users.listDirectory

import android.app.Activity
import android.content.Context
import ru.zoo.data.models.User
import ru.zoo.presentation.tables.users.listDirectory.UsersRepository.Companion.users

class UsersDB (val context: Context, val presenter: UsersPresenter, val activity: Activity) {
    init {
        getUsers()
    }

    fun getUsers() {
        val onFinish: (arrayList: ArrayList<User>) -> Unit = {
            users = it.clone() as ArrayList<User>
            presenter.setListView()
            presenter.hideLoading()
        }
        val onStart: () -> Unit = {
            presenter.showLoading()
            UsersRepository.usersTemp.clear()
        }
//        getUsersFromServer(onStart,onFinish,context,activity)
    }
}
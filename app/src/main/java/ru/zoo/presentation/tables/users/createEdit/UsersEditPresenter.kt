package ru.zoo.presentation.tables.users.createEdit

import android.app.Activity
import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_users_edit.view.*
import ru.zoo.data.Constants.REQUEST_CODE_USERS_CREATE
import ru.zoo.data.Constants.REQUEST_CODE_USERS_EDIT
import ru.zoo.data.models.User
import ru.zoo.extensions.view.IProgressView
import ru.zoo.extensions.view.gone
import ru.zoo.extensions.view.visible
import ru.zoo.presentation.tables.users.createEdit.UsersEditRepository.Companion.requestCode

class UsersEditPresenter (
    val activity: Activity,
    val context: Context,
    val layout: ConstraintLayout
) : IProgressView {
    var toolbar = layout.include_toolbar
    var progressView = layout.progress_view
    var containerUsername = layout.container_username
    var containerPassword = layout.container_password
    var containerID = layout.container_id
    var containerEmployeeID = layout.container_employeeID
    var containerRole = layout.container_role

    init {
        if (requestCode == REQUEST_CODE_USERS_CREATE){
            createPreset()
        } else if (requestCode == REQUEST_CODE_USERS_EDIT){
            editPreset()
        }
    }

    override fun showLoading() {
        progressView.visible()
    }

    override fun hideLoading() {
        progressView.gone()
    }

    fun createPreset() {

    }

    fun editPreset() {

    }
}
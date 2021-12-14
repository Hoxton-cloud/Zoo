package ru.zoo.presentation.tables.users.createEdit

import android.app.Activity
import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_users_edit.view.*
import kotlinx.android.synthetic.main.include_multiline_edit_text.view.*
import kotlinx.android.synthetic.main.include_text_parameter_without_delete.view.*
import ru.zoo.R
import ru.zoo.data.Constants.REQUEST_CODE_USERS_CREATE
import ru.zoo.data.Constants.REQUEST_CODE_USERS_EDIT
import ru.zoo.data.models.User
import ru.zoo.extensions.view.IProgressView
import ru.zoo.extensions.view.gone
import ru.zoo.extensions.view.visible
import ru.zoo.presentation.tables.users.createEdit.UsersEditRepository.Companion.employee
import ru.zoo.presentation.tables.users.createEdit.UsersEditRepository.Companion.requestCode
import ru.zoo.presentation.tables.users.createEdit.UsersEditRepository.Companion.userForSend

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
    var containerDelete = layout.frame_button_delete_user

    val db = UsersEditDB(context, this, activity)

    init {
        setMode()
    }

    fun setMode(){
        if (requestCode == REQUEST_CODE_USERS_CREATE){
            createPreset()
        } else if (requestCode == REQUEST_CODE_USERS_EDIT){
            db.getEmployee()
        }
    }

    fun editUser() {
        db.editUser()
    }

    fun addUser() {
        db.addUser()
    }

    fun getEmployee() {
        db.getEmployee()
    }

    override fun showLoading() {
        progressView.visible()
    }

    override fun hideLoading() {
        progressView.gone()
    }

    fun createPreset() {
        containerID.gone()
        containerDelete.gone()

        containerUsername.edit_text_title.text = context.getString(R.string.username)
        containerUsername.edit_text.hint = context.getString(R.string.enter_username)

        containerPassword.edit_text_title.text = context.getString(R.string.password)
        containerPassword.edit_text.hint = context.getString(R.string.enter_password)

        containerEmployeeID.label.text = context.getString(R.string.employee_id)
        containerEmployeeID.value.text = employee.firstName.toString()

        containerRole.edit_text_title.text = context.getString(R.string.role)
        containerRole.edit_text.hint = context.getString(R.string.enter_role)
    }

    fun editPreset() {
        containerID.label.text = context.getString(R.string.id)
        containerID.imageViewArrow.gone()
        containerID.value.text = userForSend.id.toString()

        containerUsername.edit_text_title.text = context.getString(R.string.username)
        containerUsername.edit_text.hint = context.getString(R.string.enter_username)
        containerUsername.edit_text.setText(userForSend.username)

        containerPassword.edit_text_title.text = context.getString(R.string.password)
        containerPassword.edit_text.hint = context.getString(R.string.enter_password)
        containerPassword.edit_text.setText(userForSend.password)

        containerEmployeeID.label.text = context.getString(R.string.employee_id)
        containerEmployeeID.value.text = employee.firstName.toString()

        containerRole.edit_text_title.text = context.getString(R.string.role)
        containerRole.edit_text.hint = context.getString(R.string.enter_role)
        containerRole.edit_text.setText(userForSend.role)
    }
}
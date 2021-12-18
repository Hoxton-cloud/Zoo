package ru.zoo.presentation.tables.employees.createEdit

import android.app.Activity
import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_employees_edit.view.*
import kotlinx.android.synthetic.main.include_multiline_edit_text.view.*
import kotlinx.android.synthetic.main.include_text_parameter_without_delete.view.*
import ru.zoo.R
import ru.zoo.data.Constants.REQUEST_CODE_CREATE
import ru.zoo.data.Constants.REQUEST_CODE_EDIT
import ru.zoo.extensions.view.IProgressView
import ru.zoo.extensions.view.gone
import ru.zoo.extensions.view.visible
import ru.zoo.presentation.tables.employees.createEdit.EmployeesEditRepository.Companion.employeeForSend
import ru.zoo.presentation.tables.employees.createEdit.EmployeesEditRepository.Companion.position
import ru.zoo.presentation.tables.employees.createEdit.EmployeesEditRepository.Companion.requestCode

class EmployeesEditPresenter (
    val activity: Activity,
    val context: Context,
    val layout: ConstraintLayout
) : IProgressView {
    var toolbar = layout.include_toolbar
    var progressView = layout.progress_view
    var containerFirstname = layout.container_firstname
    var containerLastname = layout.container_lastname
    var containerID = layout.container_id
    var containerPositionID = layout.container_positionID
    var containerPatronymic = layout.container_patronymic
    var containerPhoneNumber = layout.container_phoneNumber
    var containerDelete = layout.frame_button_delete_employee

    val db = EmployeesEditDB(context, this, activity)

    init {
        setMode()
    }

    fun setMode(){
        if (requestCode == REQUEST_CODE_CREATE){
            createPreset()
        } else if (requestCode == REQUEST_CODE_EDIT){
            db.getPosition()
        }
    }

    fun editEmployee() {
        db.editEmployee()
    }

    fun addEmployee() {
        db.addEmployee()
    }

    fun getEmployee() {
        db.getPosition()
    }

    fun deleteEmployee() {
        db.deleteEmployee()
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

        containerFirstname.edit_text_title.text = context.getString(R.string.firstname)
        containerFirstname.edit_text.hint = context.getString(R.string.enter_firstname)

        containerLastname.edit_text_title.text = context.getString(R.string.lastname)
        containerLastname.edit_text.hint = context.getString(R.string.enter_lastname)

        containerPhoneNumber.edit_text_title.text = context.getString(R.string.phone_number)
        containerPhoneNumber.edit_text.hint = context.getString(R.string.enter_phone_number)

        containerPositionID.label.text = context.getString(R.string.position)
        containerPositionID.value.visible()
        if (position.title.isEmpty()){
            containerPositionID.value.gone()
        } else {
            containerPositionID.value.visible()
            containerPositionID.value.text = position.title.toString()
        }

        containerPatronymic.edit_text_title.text = context.getString(R.string.patronymic)
        containerPatronymic.edit_text.hint = context.getString(R.string.enter_patronymic)
    }

    fun editPreset() {
        containerID.label.text = context.getString(R.string.id)
        containerID.imageViewArrow.gone()
        containerID.value.text = employeeForSend.id.toString()

        containerFirstname.edit_text_title.text = context.getString(R.string.firstname)
        containerFirstname.edit_text.hint = context.getString(R.string.enter_firstname)
        containerFirstname.edit_text.setText(employeeForSend.firstName)

        containerLastname.edit_text_title.text = context.getString(R.string.lastname)
        containerLastname.edit_text.hint = context.getString(R.string.enter_lastname)
        containerLastname.edit_text.setText(employeeForSend.lastName)

        containerPatronymic.edit_text_title.text = context.getString(R.string.patronymic)
        containerPatronymic.edit_text.hint = context.getString(R.string.enter_patronymic)
        containerPatronymic.edit_text.setText(employeeForSend.patronymic)

        containerPositionID.label.text = context.getString(R.string.position)
        containerPositionID.value.visible()
        if (position.title.isEmpty()){
            containerPositionID.value.gone()
        } else {
            containerPositionID.value.visible()
            containerPositionID.value.text = position.title.toString()
        }

        containerPhoneNumber.edit_text_title.text = context.getString(R.string.phone_number)
        containerPhoneNumber.edit_text.hint = context.getString(R.string.enter_phone_number)
        containerPhoneNumber.edit_text.setText(employeeForSend.phoneNumber)
    }



    fun readyToSend(): Boolean {
        return containerFirstname.edit_text.text.isNotEmpty() &&
                containerLastname.edit_text.text.isNotEmpty() &&
                containerPatronymic.edit_text.text.isNotEmpty() &&
                containerPhoneNumber.edit_text.text.isNotEmpty() &&
                containerPositionID.value.text.isNotEmpty()
    }
}
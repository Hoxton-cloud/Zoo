package ru.zoo.presentation.tables.users.createEdit

import android.app.Activity
import android.content.Context
import android.content.Intent
import ru.zoo.data.Constants
import ru.zoo.data.models.Employee
import ru.zoo.data.models.User
import ru.zoo.db.queries.EmployeesQueries
import ru.zoo.db.queries.UsersQueries
import ru.zoo.db.queries.deleteItem
import ru.zoo.presentation.tables.users.createEdit.UsersEditRepository.Companion.employee
import ru.zoo.presentation.tables.users.createEdit.UsersEditRepository.Companion.userForSend
import ru.zoo.presentation.tables.users.listDirectory.UsersRepository

class UsersEditDB (val context: Context, val presenter: UsersEditPresenter, val activity: Activity) {

    fun getEmployee() {
        val onStart: () -> Unit = {
            presenter.showLoading()
        }
        val onFinish: (arrayList: ArrayList<Employee>) -> Unit = {
            employee = it[0]
            if (UsersEditRepository.requestCode == Constants.REQUEST_CODE_USERS_CREATE){
                presenter.createPreset()
            } else if (UsersEditRepository.requestCode == Constants.REQUEST_CODE_USERS_EDIT){
                presenter.editPreset()
            }
            presenter.hideLoading()
        }
        EmployeesQueries().getEmployeeByIDFromServer(onStart,onFinish,context, userForSend.employeeID)
    }

    fun addUser() {
        val onStart: () -> Unit = {
            presenter.showLoading()
            UsersRepository.usersTemp.clear()
        }
        val onFinish: () -> Unit = {
            presenter.hideLoading()
            val intent = Intent()
            activity.setResult(Activity.RESULT_OK, intent)
            activity.finish()
        }
        UsersQueries().addUser(onStart,onFinish,context, userForSend)
    }

    fun editUser() {
        val onStart: () -> Unit = {
            presenter.showLoading()
            UsersRepository.usersTemp.clear()
        }
        val onFinish: () -> Unit = {
            presenter.hideLoading()
            val intent = Intent()
            activity.setResult(Activity.RESULT_OK, intent)
            activity.finish()
        }
        UsersQueries().editUserByID(onStart,onFinish,context, userForSend)
    }

    fun deleteUser() {
        val onStart: () -> Unit = {
            presenter.showLoading()
            UsersRepository.usersTemp.clear()
        }
        val onFinish: () -> Unit = {
            presenter.hideLoading()
            val intent = Intent()
            activity.setResult(Activity.RESULT_OK, intent)
            activity.finish()
        }
        deleteItem(onStart,onFinish,context, "Users", userForSend.id)
    }
}
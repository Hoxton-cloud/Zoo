package ru.zoo.presentation.tables.employees.createEdit

import android.app.Activity
import android.content.Context
import android.content.Intent
import ru.zoo.data.Constants
import ru.zoo.data.models.Position
import ru.zoo.db.queries.EmployeesQueries
import ru.zoo.db.queries.PositionsQueries
import ru.zoo.db.queries.deleteItem
import ru.zoo.presentation.tables.employees.createEdit.EmployeesEditRepository.Companion.employeeForSend
import ru.zoo.presentation.tables.employees.createEdit.EmployeesEditRepository.Companion.position
import ru.zoo.presentation.tables.employees.listDirectory.EmployeesRepository

class EmployeesEditDB (val context: Context, val presenter: EmployeesEditPresenter, val activity: Activity) {

    fun getPosition() {
        val onStart: () -> Unit = {
            presenter.showLoading()
        }
        val onFinish: (arrayList: ArrayList<Position>) -> Unit = {
            position = it[0]
            if (EmployeesEditRepository.requestCode == Constants.REQUEST_CODE_CREATE){
                presenter.createPreset()
            } else if (EmployeesEditRepository.requestCode == Constants.REQUEST_CODE_EDIT){
                presenter.editPreset()
            }
            presenter.hideLoading()
        }
        PositionsQueries().getPositionByIDFromServer(onStart,onFinish,context, employeeForSend.positionID)
    }

    fun addEmployee() {
        val onStart: () -> Unit = {
            presenter.showLoading()
            EmployeesRepository.employeesTemp.clear()
        }
        val onFinish: () -> Unit = {
            presenter.hideLoading()
            val intent = Intent()
            activity.setResult(Activity.RESULT_OK, intent)
            activity.finish()
        }
        EmployeesQueries().addEmployee(onStart,onFinish,context, employeeForSend)
    }

    fun editEmployee() {
        val onStart: () -> Unit = {
            presenter.showLoading()
            EmployeesRepository.employeesTemp.clear()
        }
        val onFinish: () -> Unit = {
            presenter.hideLoading()
            val intent = Intent()
            activity.setResult(Activity.RESULT_OK, intent)
            activity.finish()
        }
        EmployeesQueries().editEmployeeByID(onStart,onFinish,context, employeeForSend)
    }

    fun deleteEmployee() {
        val onStart: () -> Unit = {
            presenter.showLoading()
            EmployeesRepository.employeesTemp.clear()
        }
        val onFinish: () -> Unit = {
            presenter.hideLoading()
            val intent = Intent()
            activity.setResult(Activity.RESULT_OK, intent)
            activity.finish()
        }
        deleteItem(onStart,onFinish,context, "Employee", employeeForSend.id)
    }
}
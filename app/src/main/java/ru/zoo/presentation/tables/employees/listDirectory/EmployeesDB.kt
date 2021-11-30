package ru.zoo.presentation.tables.employees.listDirectory

import android.app.Activity
import android.content.Context
import ru.zoo.data.models.Employee
import ru.zoo.db.queries.EmployeesQueries
import ru.zoo.presentation.tables.employees.listDirectory.EmployeesRepository.Companion.employees

class EmployeesDB (val context: Context, val presenter: EmployeesPresenter, val activity: Activity) {
    init {
        getEmployees()
    }

    fun getEmployees() {
        val onStart: () -> Unit = {
            presenter.showLoading()
            EmployeesRepository.employeesTemp.clear()
        }
        val onFinish: (arrayList: ArrayList<Employee>) -> Unit = {
            employees = it.clone() as ArrayList<Employee>
            presenter.setListView()
            presenter.hideLoading()
        }
        EmployeesQueries().getEmployeesFromServer(onStart,onFinish,context)
    }
}
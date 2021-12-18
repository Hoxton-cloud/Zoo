package ru.zoo.presentation.tables.employees.listDirectory

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_employees.view.*
import ru.zoo.data.Constants.REQUEST_CODE_DIRECTORY
import ru.zoo.data.Constants.REQUEST_CODE_EDIT
import ru.zoo.data.Constants.REQUEST_CODE_LIST
import ru.zoo.data.models.Employee
import ru.zoo.extensions.view.IProgressView
import ru.zoo.extensions.view.gone
import ru.zoo.extensions.view.visible
import ru.zoo.presentation.tables.employees.createEdit.EmployeesEditActivity
import ru.zoo.presentation.tables.employees.listDirectory.EmployeesRepository.Companion.addEmployee
import ru.zoo.presentation.tables.employees.listDirectory.EmployeesRepository.Companion.checkedEmployee
import ru.zoo.presentation.tables.employees.listDirectory.EmployeesRepository.Companion.employees
import ru.zoo.presentation.tables.employees.listDirectory.EmployeesRepository.Companion.redEmployees
import ru.zoo.presentation.tables.employees.listDirectory.EmployeesRepository.Companion.requestCode
import ru.zoo.presentation.tables.employees.listDirectory.view.EmployeesAdapter
import java.util.*

class EmployeesPresenter (
    val activity: Activity,
    val context: Context,
    val layout: ConstraintLayout
) : IProgressView {
    val toolbar = layout.include_toolbar
    val progressView = layout.progress_view
    val recyclerView = layout.list_view_employees

    val db = EmployeesDB(context, this, activity)

    override fun hideLoading() {
        progressView.gone()
    }

    override fun showLoading() {
        progressView.visible()
    }

    fun getEmployees(){
        db.getEmployees()
    }

    fun search(s: String) {
        redEmployees.clear()
        employees.forEach {
            if (it.firstName.toLowerCase(Locale.getDefault()).contains(s)) {
                redEmployees.add(it)
            }
        }
        setListView()
    }

    fun setListView() {
        val onClick: (employee: Employee) -> Unit = { employee->
            if(requestCode == REQUEST_CODE_LIST){
                EmployeesEditActivity.startForResultEdit(
                    activity,
                    REQUEST_CODE_EDIT,
                    employee
                )
            }else if (requestCode == REQUEST_CODE_DIRECTORY) {
                if (!checkedEmployee.any { it.id == employee.id }) {
                    checkedEmployee.clear()
                }
                addEmployee(employee)
                recyclerView!!.adapter!!.notifyDataSetChanged()
                val intent = Intent()
                activity.setResult(AppCompatActivity.RESULT_OK, intent)
                activity.finish()
            }
        }
        recyclerView.adapter =
            EmployeesAdapter(redEmployees, checkedEmployee, context, onClick)
    }
}
package ru.zoo.presentation.tables.employees.listDirectory.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import ru.zoo.R
import ru.zoo.data.models.Employee

class EmployeesAdapter (
    private val employees: ArrayList<Employee>,
    private val checkedEmployee: ArrayList<Employee>,
    val context: Context,
    var onClick : ((erection: Employee) -> Unit)
) :
    RecyclerView.Adapter<EmployeesHolder>() {
    var view: View? = null
    override fun onBindViewHolder(holder: EmployeesHolder, position: Int) {
        val item = employees[position]
        val lastItem = position == employees.lastIndex
        holder.bindItem(item,checkedEmployee,onClick,lastItem)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeesHolder {
        val inflatedView = parent.inflate(R.layout.list_item_simple_card, false)
        view = inflatedView


        return EmployeesHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return employees.size
    }

    fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }
}
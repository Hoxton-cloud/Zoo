package ru.zoo.presentation.tables.employees.listDirectory

import ru.zoo.data.models.Employee

class EmployeesRepository {
    companion object{
        var employeesTemp = ArrayList<Employee>()
        fun addEmployee(employee: Employee) {
            if (checkedEmployee.any { it.id == employee.id }) {
                checkedEmployee.removeAll { it.id == employee.id }
            } else {
                checkedEmployee.add(employee)
            }
        }
        var employees = ArrayList<Employee>()
        var redEmployees = ArrayList<Employee>()
        var checkedEmployee = ArrayList<Employee>()

        var requestCode = 0
        var isMultiple = false

        fun clear() {
            employees.clear()
            redEmployees.clear()
            employeesTemp.clear()
        }
    }
}
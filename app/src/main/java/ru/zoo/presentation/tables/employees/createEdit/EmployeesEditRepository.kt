package ru.zoo.presentation.tables.employees.createEdit

import ru.zoo.data.models.Employee
import ru.zoo.data.models.Position

class EmployeesEditRepository {
    companion object{
        var employeeForSend = Employee()
        var requestCode = 0
        var employeeID : Int? = null
        var position = Position()
    }
}
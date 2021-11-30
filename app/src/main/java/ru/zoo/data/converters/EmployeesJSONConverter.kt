package ru.zoo.data.converters

import com.google.gson.JsonParseException
import org.json.JSONArray
import ru.zoo.data.models.Employee

class EmployeesJSONConverter{
    @Throws(JsonParseException::class)
    fun deserializeList(
        json: JSONArray
    ): ArrayList<Employee> {
        val arrayList = ArrayList<Employee>()
        for (i in 0 until json.length()) {
            val jsonObject = json.getJSONObject(i)
            val employee = Employee()
            employee.id = jsonObject.getInt("id")
            employee.positionID = jsonObject.getInt("positionID")
            employee.firstName = jsonObject.getString("firstName")
            employee.lastName = jsonObject.getString("lastName")
            employee.patronymic = jsonObject.getString("patronymic")
            employee.phoneNumber = jsonObject.getString("phoneNumber")

            arrayList.add(employee)
        }
        return arrayList
    }
}
package ru.zoo.data.converters

import com.google.gson.JsonParseException
import org.json.JSONArray
import ru.zoo.data.models.User

class UsersJSONConverter{
    @Throws(JsonParseException::class)
    fun deserializeList(
        json: JSONArray
    ): ArrayList<User> {
        val arrayList = ArrayList<User>()
        for (i in 0 until json.length()) {
            val jsonObject = json.getJSONObject(i)
            val user = User()
            user.id = jsonObject.getInt("id")
            user.username = jsonObject.getString("username")
            user.password = jsonObject.getString("password")
            user.token = jsonObject.getString("token")
            user.employeeID = jsonObject.getInt("employeeID")
            user.role = jsonObject.getString("role")

            arrayList.add(user)
        }
        return arrayList
    }
}
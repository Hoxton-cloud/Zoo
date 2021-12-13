package ru.zoo.data.converters

import com.google.gson.JsonParseException
import org.json.JSONArray
import ru.zoo.data.models.Checkin

class CheckinsJSONConverter {
    @Throws(JsonParseException::class)
    fun deserializeList(
        json: JSONArray
    ): ArrayList<Checkin> {
        val arrayList = ArrayList<Checkin>()
        for (i in 0 until json.length()) {
            val jsonObject = json.getJSONObject(i)
            val checkin = Checkin()
            checkin.id = jsonObject.getInt("id")
            checkin.date = jsonObject.getString("date")
            checkin.animalID = jsonObject.getInt("animalID")
            checkin.employeeID = jsonObject.getInt("employeeID")
            checkin.aviaryID = jsonObject.getInt("aviaryID")

            arrayList.add(checkin)
        }
        return arrayList
    }
}
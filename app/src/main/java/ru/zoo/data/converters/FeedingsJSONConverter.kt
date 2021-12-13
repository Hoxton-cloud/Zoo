package ru.zoo.data.converters

import com.google.gson.JsonParseException
import org.json.JSONArray
import ru.zoo.data.models.Feeding

class FeedingsJSONConverter {
    @Throws(JsonParseException::class)
    fun deserializeList(
        json: JSONArray
    ): ArrayList<Feeding> {
        val arrayList = ArrayList<Feeding>()
        for (i in 0 until json.length()) {
            val jsonObject = json.getJSONObject(i)
            val feeding = Feeding()
            feeding.id = jsonObject.getInt("id")
            feeding.employeeID = jsonObject.getInt("employeeID")
            feeding.animalID = jsonObject.getInt("animalID")
            feeding.rationID = jsonObject.getInt("rationID")

            arrayList.add(feeding)
        }
        return arrayList
    }
}
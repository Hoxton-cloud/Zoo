package ru.zoo.data.converters

import com.google.gson.JsonParseException
import org.json.JSONArray
import ru.zoo.data.models.Position

class PositionsJSONConverter {
    @Throws(JsonParseException::class)
    fun deserializeList(
        json: JSONArray
    ): ArrayList<Position> {
        val arrayList = ArrayList<Position>()
        for (i in 0 until json.length()) {
            val jsonObject = json.getJSONObject(i)
            val position = Position()
            position.id = jsonObject.getInt("id")
            position.title = jsonObject.getString("title")
            position.salary = jsonObject.getString("salary")

            arrayList.add(position)
        }
        return arrayList
    }
}
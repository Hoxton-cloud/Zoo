package ru.zoo.data.converters

import com.google.gson.JsonParseException
import org.json.JSONArray
import ru.zoo.data.models.Aviary

class AviariesJSONConverter {
    @Throws(JsonParseException::class)
    fun deserializeList(
        json: JSONArray
    ): ArrayList<Aviary> {
        val arrayList = ArrayList<Aviary>()
        for (i in 0 until json.length()) {
            val jsonObject = json.getJSONObject(i)
            val aviary = Aviary()
            aviary.id = jsonObject.getInt("id")
            aviary.number = jsonObject.getString("number")
            aviary.typeID = jsonObject.getInt("typeID")

            arrayList.add(aviary)
        }
        return arrayList
    }
}
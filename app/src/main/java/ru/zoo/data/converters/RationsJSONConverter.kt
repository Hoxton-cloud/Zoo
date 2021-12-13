package ru.zoo.data.converters

import com.google.gson.JsonParseException
import org.json.JSONArray
import ru.zoo.data.models.Ration

class RationsJSONConverter{
    @Throws(JsonParseException::class)
    fun deserializeList(
        json: JSONArray
    ): ArrayList<Ration> {
        val arrayList = ArrayList<Ration>()
        for (i in 0 until json.length()) {
            val jsonObject = json.getJSONObject(i)
            val ration = Ration()
            ration.id = jsonObject.getInt("id")
            ration.feedID = jsonObject.getInt("feedID")
            ration.animalID = jsonObject.getInt("animalID")
            ration.time = jsonObject.getString("time")
            ration.mass = jsonObject.getString("mass")

            arrayList.add(ration)
        }
        return arrayList
    }
}
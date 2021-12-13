package ru.zoo.data.converters

import com.google.gson.JsonParseException
import org.json.JSONArray
import ru.zoo.data.models.ClimateZone

class ClimateZonesJSONConverter {
    @Throws(JsonParseException::class)
    fun deserializeList(
        json: JSONArray
    ): ArrayList<ClimateZone> {
        val arrayList = ArrayList<ClimateZone>()
        for (i in 0 until json.length()) {
            val jsonObject = json.getJSONObject(i)
            val climateZone = ClimateZone()
            climateZone.id = jsonObject.getInt("id")
            climateZone.title = jsonObject.getString("title")

            arrayList.add(climateZone)
        }
        return arrayList
    }
}
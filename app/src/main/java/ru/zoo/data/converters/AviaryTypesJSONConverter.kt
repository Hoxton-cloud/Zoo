package ru.zoo.data.converters

import com.google.gson.JsonParseException
import org.json.JSONArray
import ru.zoo.data.models.AviaryType

class AviaryTypesJSONConverter {
    @Throws(JsonParseException::class)
    fun deserializeList(
        json: JSONArray
    ): ArrayList<AviaryType> {
        val arrayList = ArrayList<AviaryType>()
        for (i in 0 until json.length()) {
            val jsonObject = json.getJSONObject(i)
            val aviaryType = AviaryType()
            aviaryType.id = jsonObject.getInt("id")
            aviaryType.title = jsonObject.getString("title")

            arrayList.add(aviaryType)
        }
        return arrayList
    }
}
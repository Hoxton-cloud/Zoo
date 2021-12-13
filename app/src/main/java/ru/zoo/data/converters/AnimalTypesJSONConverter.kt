package ru.zoo.data.converters

import com.google.gson.JsonParseException
import org.json.JSONArray
import ru.zoo.data.models.AnimalType

class AnimalTypesJSONConverter {
    @Throws(JsonParseException::class)
    fun deserializeList(
        json: JSONArray
    ): ArrayList<AnimalType> {
        val arrayList = ArrayList<AnimalType>()
        for (i in 0 until json.length()) {
            val jsonObject = json.getJSONObject(i)
            val animalType = AnimalType()
            animalType.id = jsonObject.getInt("id")
            animalType.title = jsonObject.getString("title")

            arrayList.add(animalType)
        }
        return arrayList
    }
}
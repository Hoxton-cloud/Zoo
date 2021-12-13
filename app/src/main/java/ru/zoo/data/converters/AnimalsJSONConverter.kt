package ru.zoo.data.converters

import com.google.gson.JsonParseException
import org.json.JSONArray
import ru.zoo.data.models.Animal

class AnimalsJSONConverter {
    @Throws(JsonParseException::class)
    fun deserializeList(
        json: JSONArray
    ): ArrayList<Animal> {
        val arrayList = ArrayList<Animal>()
        for (i in 0 until json.length()) {
            val jsonObject = json.getJSONObject(i)
            val animal = Animal()
            animal.id = jsonObject.getInt("id")
            animal.name = jsonObject.getString("name")
            animal.sex = jsonObject.getString("sex")
            animal.speciesID = jsonObject.getInt("speciesID")

            arrayList.add(animal)
        }
        return arrayList
    }
}
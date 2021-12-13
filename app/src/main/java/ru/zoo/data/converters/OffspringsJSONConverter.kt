package ru.zoo.data.converters

import com.google.gson.JsonParseException
import org.json.JSONArray
import ru.zoo.data.models.Offspring

class OffspringsJSONConverter {
    @Throws(JsonParseException::class)
    fun deserializeList(
        json: JSONArray
    ): ArrayList<Offspring> {
        val arrayList = ArrayList<Offspring>()
        for (i in 0 until json.length()) {
            val jsonObject = json.getJSONObject(i)
            val offspring = Offspring()
            offspring.id = jsonObject.getInt("id")
            offspring.amount = jsonObject.getString("amount")
            offspring.animalID = jsonObject.getInt("animalID")

            arrayList.add(offspring)
        }
        return arrayList
    }
}
package ru.zoo.data.converters

import com.google.gson.JsonParseException
import org.json.JSONArray
import ru.zoo.data.models.AnimalExchange

class AnimalExchangesJSONConverter {
    @Throws(JsonParseException::class)
    fun deserializeList(
        json: JSONArray
    ): ArrayList<AnimalExchange> {
        val arrayList = ArrayList<AnimalExchange>()
        for (i in 0 until json.length()) {
            val jsonObject = json.getJSONObject(i)
            val animalExchange = AnimalExchange()
            animalExchange.id = jsonObject.getInt("id")
            animalExchange.title = jsonObject.getString("title")

            arrayList.add(animalExchange)
        }
        return arrayList
    }
}
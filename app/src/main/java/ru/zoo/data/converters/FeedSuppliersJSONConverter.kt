package ru.zoo.data.converters

import com.google.gson.JsonParseException
import org.json.JSONArray
import ru.zoo.data.models.FeedSupply

class FeedSuppliersJSONConverter {
    @Throws(JsonParseException::class)
    fun deserializeList(
        json: JSONArray
    ): ArrayList<FeedSupply> {
        val arrayList = ArrayList<FeedSupply>()
        for (i in 0 until json.length()) {
            val jsonObject = json.getJSONObject(i)
            val feedSupply = FeedSupply()
            feedSupply.id = jsonObject.getInt("id")
            feedSupply.quantity = jsonObject.getString("quantity")
            feedSupply.price = jsonObject.getString("price")
            feedSupply.feedID = jsonObject.getInt("feedID")
            feedSupply.supplierID = jsonObject.getInt("supplierID")

            arrayList.add(feedSupply)
        }
        return arrayList
    }
}
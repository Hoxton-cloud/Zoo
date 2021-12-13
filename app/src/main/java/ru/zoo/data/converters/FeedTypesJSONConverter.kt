package ru.zoo.data.converters

import com.google.gson.JsonParseException
import org.json.JSONArray
import ru.zoo.data.models.FeedType

class FeedTypesJSONConverter {
    @Throws(JsonParseException::class)
    fun deserializeList(
        json: JSONArray
    ): ArrayList<FeedType> {
        val arrayList = ArrayList<FeedType>()
        for (i in 0 until json.length()) {
            val jsonObject = json.getJSONObject(i)
            val feedType = FeedType()
            feedType.id = jsonObject.getInt("id")
            feedType.title = jsonObject.getString("title")

            arrayList.add(feedType)
        }
        return arrayList
    }
}
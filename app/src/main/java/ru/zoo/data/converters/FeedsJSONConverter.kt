package ru.zoo.data.converters

import com.google.gson.JsonParseException
import org.json.JSONArray
import ru.zoo.data.models.Feed

class FeedsJSONConverter {
    @Throws(JsonParseException::class)
    fun deserializeList(
        json: JSONArray
    ): ArrayList<Feed> {
        val arrayList = ArrayList<Feed>()
        for (i in 0 until json.length()) {
            val jsonObject = json.getJSONObject(i)
            val feed = Feed()
            feed.id = jsonObject.getInt("id")
            feed.title = jsonObject.getString("title")
            feed.typeID = jsonObject.getInt("typeID")

            arrayList.add(feed)
        }
        return arrayList
    }
}
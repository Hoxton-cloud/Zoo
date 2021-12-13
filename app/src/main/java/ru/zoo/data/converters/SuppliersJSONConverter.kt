package ru.zoo.data.converters

import com.google.gson.JsonParseException
import org.json.JSONArray
import ru.zoo.data.models.Supplier

class SuppliersJSONConverter {
    @Throws(JsonParseException::class)
    fun deserializeList(
        json: JSONArray
    ): ArrayList<Supplier> {
        val arrayList = ArrayList<Supplier>()
        for (i in 0 until json.length()) {
            val jsonObject = json.getJSONObject(i)
            val supplier = Supplier()
            supplier.id = jsonObject.getInt("id")
            supplier.title = jsonObject.getString("title")

            arrayList.add(supplier)
        }
        return arrayList
    }
}
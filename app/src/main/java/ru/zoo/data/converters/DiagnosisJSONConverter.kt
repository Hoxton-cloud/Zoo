package ru.zoo.data.converters

import com.google.gson.JsonParseException
import org.json.JSONArray
import ru.zoo.data.models.Diagnosis

class DiagnosisJSONConverter {
    @Throws(JsonParseException::class)
    fun deserializeList(
        json: JSONArray
    ): ArrayList<Diagnosis> {
        val arrayList = ArrayList<Diagnosis>()
        for (i in 0 until json.length()) {
            val jsonObject = json.getJSONObject(i)
            val diagnosis = Diagnosis()
            diagnosis.id = jsonObject.getInt("id")
            diagnosis.title = jsonObject.getString("title")

            arrayList.add(diagnosis)
        }
        return arrayList
    }
}
package ru.zoo.data.converters

import com.google.gson.JsonParseException
import org.json.JSONArray
import ru.zoo.data.models.MedicalExamination

class MedicalExaminationsJSONConverter {
    @Throws(JsonParseException::class)
    fun deserializeList(
        json: JSONArray
    ): ArrayList<MedicalExamination> {
        val arrayList = ArrayList<MedicalExamination>()
        for (i in 0 until json.length()) {
            val jsonObject = json.getJSONObject(i)
            val medicalExamination = MedicalExamination()
            medicalExamination.id = jsonObject.getInt("id")
            medicalExamination.date = jsonObject.getString("date")
            medicalExamination.animalID = jsonObject.getInt("animalID")
            medicalExamination.diagnosisID = jsonObject.getInt("diagnosisID")

            arrayList.add(medicalExamination)
        }
        return arrayList
    }
}
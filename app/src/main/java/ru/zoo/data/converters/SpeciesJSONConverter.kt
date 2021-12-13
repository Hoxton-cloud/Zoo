//package ru.zoo.data.converters
//
//import com.google.gson.JsonParseException
//import org.json.JSONArray
//import ru.zoo.data.models.Species
//
//class SpeciesJSONConverter {
//    @Throws(JsonParseException::class)
//    fun deserializeList(
//        json: JSONArray
//    ): ArrayList<Species> {
//        val arrayList = ArrayList<Species>()
//        for (i in 0 until json.length()) {
//            val jsonObject = json.getJSONObject(i)
//            val species = Species()
//            species.id = jsonObject.getInt("id")
//            species.title = jsonObject.getString("title")
//
//            arrayList.add(species)
//        }
//        return arrayList
//    }
//}
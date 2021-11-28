package ru.zoo.data.converters

import android.content.Context
import com.google.gson.JsonParseException
import org.json.JSONArray
import org.json.JSONObject
import ru.zoo.data.models.*
import java.lang.Exception

@Throws(JsonParseException::class)
fun deserializeSimplest(
    json: JSONObject
): Simplest {
    val simple = Simplest()
    simple.id = nullChecker(json.getString("id"))
    simple.name = nullChecker(json.getString("name"))
    return simple
}

@Throws(JsonParseException::class)
fun deserializeID(
    jsonObject: JSONArray
):Int{
    return jsonObject.getInt(0)
}
package ru.zoo.data.converters

import android.content.Context
import com.google.gson.*
import org.json.JSONObject
import ru.zoo.data.Preferences
import java.lang.Exception


class LoginJSONConverter {

    @Throws(JsonParseException::class)
    fun deserialize(
        json: JSONObject,context: Context
    ){
        try {
            Preferences.setUserId(context,json.getString("username"))
            Preferences.setUserToken(context,json.getString("token"))
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}
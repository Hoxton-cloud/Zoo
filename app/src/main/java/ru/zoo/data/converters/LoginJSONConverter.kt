package ru.zoo.data.converters

import android.content.Context
import com.google.gson.*
import org.json.JSONObject
import ru.zoo.data.Constants.role
import ru.zoo.data.Preferences
import java.lang.Exception


class LoginJSONConverter {

    @Throws(JsonParseException::class)
    fun deserialize(
        json: JSONObject,context: Context
    ){
        try {
            Preferences.setUserId(context,json.getInt("id"))
            Preferences.setUserToken(context,json.getString("token"))
            role = json.getString("role")

        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}
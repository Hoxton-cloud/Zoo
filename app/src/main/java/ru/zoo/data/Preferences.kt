package ru.zoo.data

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

object Preferences {
    const val USER_LOGIN = "userLogin"
    const val USER_PASSWORD = "userPassword"
    const val USER_ENTER = "enters"
    const val USER_ID = "userID"
    const val COMPANY_ID = "companyID"
    const val USER_TOKEN = "USER_TOKEN"
    const val IP_ADDRESS = "IP_ADDRESS"
    const val IS_DEV = "IS_DEV"

    const val STORAGE_NAME = "storage_name"
    var sharedPreferences: SharedPreferences? = null
    fun initialize(context: Context) {
        sharedPreferences = context.getSharedPreferences(
            STORAGE_NAME,
            Activity.MODE_PRIVATE
        )
    }

    fun putString(key: String?, value: String?) {
        val editor =
            sharedPreferences!!.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(key: String?, defaultValue: String?): String? {
        return sharedPreferences!!.getString(key, defaultValue)
    }

    fun putBoolean(key: String?, value: Boolean?) {
        val editor =
            sharedPreferences!!.edit()
        editor.putBoolean(key, value!!)
        editor.apply()
    }

    fun getBoolean(key: String?, defaultValue: Boolean?): Boolean? {
        return sharedPreferences!!.getBoolean(key, defaultValue!!)
    }

    fun getUserId(context: Context): String? {
        initialize(context)
        return getString(
            USER_ID,
            ""
        )
    }

    fun getUserLogin(context: Context): String? {
        initialize(context)
        return getString(
            USER_LOGIN,
            ""
        )
    }

    fun getUserPassword(context: Context): String? {
        initialize(context)
        return getString(
            USER_PASSWORD,
            ""
        )
    }

    fun getIpAddress(context: Context): String? {
        initialize(context)
        return getString(
            IP_ADDRESS,
            "https://sid-t.nipigas.ru/"
        )
    }

    fun getCompanyId(context: Context): String? {
        initialize(context)
        return getString(
            COMPANY_ID,
            ""
        )
    }

    fun getUserToken(context: Context): String? {
        initialize(context)
        return getString(
            USER_TOKEN,
            ""
        )
    }

    fun setUserToken(context: Context, string: String) {
        initialize(context)
        putString(USER_TOKEN, string)
    }

    fun setUserPassword(context: Context, string: String) {
        initialize(context)
        putString(USER_PASSWORD, string)
    }

    fun setUserLogin(context: Context, string: String) {
        initialize(context)
        putString(USER_LOGIN, string)
    }

    fun setUserIpAddress(context: Context, string: String) {
        initialize(context)
        putString(IP_ADDRESS, string)
    }
}
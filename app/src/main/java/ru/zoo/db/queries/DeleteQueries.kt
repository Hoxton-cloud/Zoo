package ru.zoo.db.queries

import android.content.Context
import android.util.Log
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.zoo.data.Preferences
import ru.zoo.data.converters.LoginJSONConverter
import ru.zoo.db.getLoginClient
import ru.zoo.db.getQueryClient
import ru.zoo.db.services.DeleteService
import ru.zoo.db.services.LoginService
import ru.zoo.extensions.view.ToastMaker
import java.lang.Exception
import java.util.*


fun deleteAll(
    onStart: () -> Unit,
    onFinish: () -> Unit,
    context: Context,
    table: String
) {
    val deleteService =
        getQueryClient(context).create(DeleteService::class.java)
    Preferences.initialize(context)
    val userId = Preferences.getInt(Preferences.USER_ID, 999999)
    val token = Preferences.getString(Preferences.USER_TOKEN, "")
    val call =
        deleteService.deleteAll(token, userId, table)
    call.enqueue(object : Callback<ResponseBody> {
        override fun onResponse(
            call: Call<ResponseBody>,
            response: Response<ResponseBody>
        ) {
            if (response.isSuccessful) {
                onFinish.invoke()
            } else {
                Log.e("error", response.errorBody()!!.string())
                onFinish.invoke()
            }

        }

        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            Log.d("fail", t.message + t.localizedMessage)
            ToastMaker().toastErrorConnect(context)
            onFinish.invoke()
            call.cancel()
        }
    })
}

fun deleteItem(
    onStart: () -> Unit,
    onFinish: () -> Unit,
    context: Context,
    table: String,
    itemId: Int
) {
    val deleteService =
        getQueryClient(context).create(DeleteService::class.java)
    Preferences.initialize(context)
    val userId = Preferences.getInt(Preferences.USER_ID, 9999999)
    val token = Preferences.getString(Preferences.USER_TOKEN, "")
    val call =
        deleteService.deleteIdItem(token, userId, table,itemId)
    call.enqueue(object : Callback<ResponseBody> {
        override fun onResponse(
            call: Call<ResponseBody>,
            response: Response<ResponseBody>
        ) {
            if (response.isSuccessful) {
                onFinish.invoke()
            } else {
                Log.e("error", response.errorBody()!!.string())
                onFinish.invoke()
            }

        }

        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            Log.d("fail", t.message + t.localizedMessage)
            ToastMaker().toastErrorConnect(context)
            onFinish.invoke()
            call.cancel()
        }
    })
}
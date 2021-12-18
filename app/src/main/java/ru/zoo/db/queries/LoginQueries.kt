package ru.zoo.db.queries

import android.content.Context
import android.util.Log
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.zoo.data.converters.LoginJSONConverter
import ru.zoo.db.getLoginClient
import ru.zoo.db.services.LoginService
import ru.zoo.extensions.view.ToastMaker
import java.lang.Exception
import java.util.*


fun loginToServer(
    onStart: () -> Unit,
    onFinish: (isCorrect: Boolean,isErrorCredentials:Boolean) -> Unit,
    context: Context,
    login: String,
    password:String
) {
    onStart.invoke()
    val loginService = getLoginClient().create(LoginService::class.java)
    val call = loginService.login(
       login.removePrefix('\''.toString()).replace(" ", ""), password.replace(" ", "")
    )
    call.enqueue(object : Callback<ResponseBody> {

        override fun onResponse(
            call: Call<ResponseBody>,
            response: Response<ResponseBody>
        ) {
            if (response.isSuccessful) {
                val remoteResponse = response.body()!!.string()
                try {
                    if (remoteResponse.isEmpty() || remoteResponse == "[]"){
                        onFinish.invoke(false,true)
                    } else{
                        val profileFileUploadResponse = JSONArray(remoteResponse).getJSONObject(0)
                        LoginJSONConverter().deserialize(profileFileUploadResponse, context)
                        onFinish.invoke(true,false)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    onFinish.invoke(false,true)
                }
            } else {
                val remoteResponse = response.errorBody()!!.string()
                Log.e("s", remoteResponse)
                try {
                    if (remoteResponse.toLowerCase(Locale.getDefault())
                            .contains("invalid login or password")
                    ) {
                        onFinish.invoke(false,true)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            t.printStackTrace()
            ToastMaker().toastErrorConnect(context)
            onFinish.invoke(false,false)
            call.cancel()
        }
    }
    )
}
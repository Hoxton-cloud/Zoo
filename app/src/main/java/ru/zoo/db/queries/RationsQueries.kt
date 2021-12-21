package ru.zoo.db.queries

import android.content.Context
import android.util.Log
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.zoo.data.Preferences
import ru.zoo.data.converters.RationsJSONConverter
import ru.zoo.data.models.Ration
import ru.zoo.db.getQueryClient
import ru.zoo.db.services.RationService
import ru.zoo.extensions.view.ToastMaker

class RationsQueries {
    fun getRationsFromServer(
        onStart: () -> Unit,
        onFinish: (ArrayList<Ration>) -> Unit,
        context: Context
    ) {
        onStart.invoke()
        var rations = ArrayList<Ration>()
        val rationService =
            getQueryClient(context).create(RationService::class.java)
        val userId = Preferences.getInt(Preferences.USER_ID, 999999)
        val token = Preferences.getString(Preferences.USER_TOKEN, "")
        val call = rationService.getRationsList(token, userId)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                if (response.isSuccessful) {
                    try {
                        val remoteResponse: String = response.body()!!.string()
                        val jsonArray = JSONArray(remoteResponse)
                        rations = RationsJSONConverter()
                            .deserializeList(jsonArray)
                        onFinish.invoke(rations)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }

                } else {
                    Log.e("error", response.errorBody()!!.string())
                }

            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("fail", t.message + t.localizedMessage)
                ToastMaker().toastErrorConnect(context)
                onFinish.invoke(rations)
                call.cancel()
            }
        })
    }

    fun getRationByIDFromServer(
        onStart: () -> Unit,
        onFinish: (ArrayList<Ration>) -> Unit,
        context: Context,
        soughtRationID: Int
    ) {
        onStart.invoke()
        var rations = ArrayList<Ration>()
        val rationService =
            getQueryClient(context).create(RationService::class.java)
        val userId = Preferences.getInt(Preferences.USER_ID, 99999999)
        val token = Preferences.getString(Preferences.USER_TOKEN, "")
        val call = rationService.getRationByID(token, userId, soughtRationID)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                if (response.isSuccessful) {
                    try {
                        val remoteResponse: String = response.body()!!.string()
                        val jsonArray = JSONArray(remoteResponse)
                        rations = RationsJSONConverter()
                            .deserializeList(jsonArray)
                        onFinish.invoke(rations)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                        onFinish.invoke(rations)
                    }

                } else {
                    Log.e("error", response.errorBody()!!.string())
                    onFinish.invoke(rations)
                }

            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("fail", t.message + t.localizedMessage)
                ToastMaker().toastErrorConnect(context)
                onFinish.invoke(rations)
                call.cancel()
            }
        })
    }

    fun editRationByID(
        onStart: () -> Unit,
        onFinish: () -> Unit,
        context: Context,
        ration: Ration
    ) {
        onStart.invoke()
        val rationService =
            getQueryClient(context).create(RationService::class.java)
        val userId = Preferences.getInt(Preferences.USER_ID, 9999999)
        val token = Preferences.getString(Preferences.USER_TOKEN, "")
        val call = rationService.editRation(
            token,
            userId,
            ration.feedID,
            ration.animalID,
            ration.time,
            ration.mass,
            ration.id)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                if (response.isSuccessful) {
                    try {
                        val remoteResponse: String = response.body()!!.string()
                        onFinish.invoke()
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }

                } else {
                    Log.e("error", response.errorBody()!!.string())
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

    fun addRation(
        onStart: () -> Unit,
        onFinish: () -> Unit,
        context: Context,
        ration: Ration
    ) {
        onStart.invoke()
        val rationService =
            getQueryClient(context).create(RationService::class.java)
        val userId = Preferences.getInt(Preferences.USER_ID, 999999)
        val token = Preferences.getString(Preferences.USER_TOKEN, "")
        val call = rationService.addRation(
            token,
            userId,
            ration.feedID,
            ration.animalID,
            ration.time,
            ration.mass
        )
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                if (response.isSuccessful) {
                    try {
                        val remoteResponse: String = response.body()!!.string()
                        onFinish.invoke()
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }

                } else {
                    Log.e("error", response.errorBody()!!.string())
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
}
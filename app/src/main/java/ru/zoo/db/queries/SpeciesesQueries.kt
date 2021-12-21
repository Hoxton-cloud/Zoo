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
import ru.zoo.data.converters.SpeciesJSONConverter
import ru.zoo.data.models.Species
import ru.zoo.db.getQueryClient
import ru.zoo.db.services.SpeciesService
import ru.zoo.extensions.view.ToastMaker

class SpeciesesQueries {
    fun getSpeciesesFromServer(
        onStart: () -> Unit,
        onFinish: (ArrayList<Species>) -> Unit,
        context: Context
    ) {
        onStart.invoke()
        var specieses = ArrayList<Species>()
        val speciesService =
            getQueryClient(context).create(SpeciesService::class.java)
        val userId = Preferences.getInt(Preferences.USER_ID, 999999)
        val token = Preferences.getString(Preferences.USER_TOKEN, "")
        val call = speciesService.getSpeciesesList(token, userId)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                if (response.isSuccessful) {
                    try {
                        val remoteResponse: String = response.body()!!.string()
                        val jsonArray = JSONArray(remoteResponse)
                        specieses = SpeciesJSONConverter()
                            .deserializeList(jsonArray)
                        onFinish.invoke(specieses)
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
                onFinish.invoke(specieses)
                call.cancel()
            }
        })
    }

    fun getSpeciesByIDFromServer(
        onStart: () -> Unit,
        onFinish: (ArrayList<Species>) -> Unit,
        context: Context,
        soughtSpeciesID: Int
    ) {
        onStart.invoke()
        var specieses = ArrayList<Species>()
        val speciesService =
            getQueryClient(context).create(SpeciesService::class.java)
        val userId = Preferences.getInt(Preferences.USER_ID, 99999999)
        val token = Preferences.getString(Preferences.USER_TOKEN, "")
        val call = speciesService.getSpeciesByID(token, userId, soughtSpeciesID)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                if (response.isSuccessful) {
                    try {
                        val remoteResponse: String = response.body()!!.string()
                        val jsonArray = JSONArray(remoteResponse)
                        specieses = SpeciesJSONConverter()
                            .deserializeList(jsonArray)
                        onFinish.invoke(specieses)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                        onFinish.invoke(specieses)
                    }

                } else {
                    Log.e("error", response.errorBody()!!.string())
                    onFinish.invoke(specieses)
                }

            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("fail", t.message + t.localizedMessage)
                ToastMaker().toastErrorConnect(context)
                onFinish.invoke(specieses)
                call.cancel()
            }
        })
    }

    fun editSpeciesByID(
        onStart: () -> Unit,
        onFinish: () -> Unit,
        context: Context,
        species: Species
    ) {
        onStart.invoke()
        val speciesService =
            getQueryClient(context).create(SpeciesService::class.java)
        val userId = Preferences.getInt(Preferences.USER_ID, 9999999)
        val token = Preferences.getString(Preferences.USER_TOKEN, "")
        val call = speciesService.editSpecies(
            token,
            userId,
            species.title,
            species.climateZoneID,
            species.typeID,
            species.id)
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

    fun addSpecies(
        onStart: () -> Unit,
        onFinish: () -> Unit,
        context: Context,
        species: Species
    ) {
        onStart.invoke()
        val speciesService =
            getQueryClient(context).create(SpeciesService::class.java)
        val userId = Preferences.getInt(Preferences.USER_ID, 999999)
        val token = Preferences.getString(Preferences.USER_TOKEN, "")
        val call = speciesService.addSpecies(
            token,
            userId,
            species.title,
            species.climateZoneID,
            species.typeID
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
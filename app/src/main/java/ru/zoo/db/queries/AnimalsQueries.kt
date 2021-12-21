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
import ru.zoo.data.converters.AnimalsJSONConverter
import ru.zoo.data.models.Animal
import ru.zoo.db.getQueryClient
import ru.zoo.db.services.AnimalService
import ru.zoo.extensions.view.ToastMaker

class AnimalsQueries {
    fun getAnimalsFromServer(
        onStart: () -> Unit,
        onFinish: (ArrayList<Animal>) -> Unit,
        context: Context
    ) {
        onStart.invoke()
        var animals = ArrayList<Animal>()
        val animalService =
            getQueryClient(context).create(AnimalService::class.java)
        val userId = Preferences.getInt(Preferences.USER_ID, 999999)
        val token = Preferences.getString(Preferences.USER_TOKEN, "")
        val call = animalService.getAnimalsList(token, userId)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                if (response.isSuccessful) {
                    try {
                        val remoteResponse: String = response.body()!!.string()
                        val jsonArray = JSONArray(remoteResponse)
                        animals = AnimalsJSONConverter()
                            .deserializeList(jsonArray)
                        onFinish.invoke(animals)
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
                onFinish.invoke(animals)
                call.cancel()
            }
        })
    }

    fun getAnimalByIDFromServer(
        onStart: () -> Unit,
        onFinish: (ArrayList<Animal>) -> Unit,
        context: Context,
        soughtAnimalID: Int
    ) {
        onStart.invoke()
        var animals = ArrayList<Animal>()
        val animalService =
            getQueryClient(context).create(AnimalService::class.java)
        val userId = Preferences.getInt(Preferences.USER_ID, 99999999)
        val token = Preferences.getString(Preferences.USER_TOKEN, "")
        val call = animalService.getAnimalByID(token, userId, soughtAnimalID)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                if (response.isSuccessful) {
                    try {
                        val remoteResponse: String = response.body()!!.string()
                        val jsonArray = JSONArray(remoteResponse)
                        animals = AnimalsJSONConverter()
                            .deserializeList(jsonArray)
                        onFinish.invoke(animals)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                        onFinish.invoke(animals)
                    }

                } else {
                    Log.e("error", response.errorBody()!!.string())
                    onFinish.invoke(animals)
                }

            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("fail", t.message + t.localizedMessage)
                ToastMaker().toastErrorConnect(context)
                onFinish.invoke(animals)
                call.cancel()
            }
        })
    }

    fun editAnimalByID(
        onStart: () -> Unit,
        onFinish: () -> Unit,
        context: Context,
        animal: Animal
    ) {
        onStart.invoke()
        val animalService =
            getQueryClient(context).create(AnimalService::class.java)
        val userId = Preferences.getInt(Preferences.USER_ID, 9999999)
        val token = Preferences.getString(Preferences.USER_TOKEN, "")
        val call = animalService.editAnimal(
            token,
            userId,
            animal.name,
            animal.sex,
            animal.speciesID,
            animal.id)
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

    fun addAnimal(
        onStart: () -> Unit,
        onFinish: () -> Unit,
        context: Context,
        animal: Animal
    ) {
        onStart.invoke()
        val animalService =
            getQueryClient(context).create(AnimalService::class.java)
        val userId = Preferences.getInt(Preferences.USER_ID, 999999)
        val token = Preferences.getString(Preferences.USER_TOKEN, "")
        val call = animalService.addAnimal(
            token,
            userId,
            animal.name,
            animal.sex,
            animal.speciesID
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
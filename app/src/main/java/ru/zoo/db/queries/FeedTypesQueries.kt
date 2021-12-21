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
import ru.zoo.data.converters.FeedTypesJSONConverter
import ru.zoo.data.models.FeedType
import ru.zoo.db.getQueryClient
import ru.zoo.db.services.FeedTypeService
import ru.zoo.extensions.view.ToastMaker

class FeedTypesQueries {
    fun getFeedTypesFromServer(
        onStart: () -> Unit,
        onFinish: (ArrayList<FeedType>) -> Unit,
        context: Context
    ) {
        onStart.invoke()
        var feedTypes = ArrayList<FeedType>()
        val feedTypeService =
            getQueryClient(context).create(FeedTypeService::class.java)
        val userId = Preferences.getInt(Preferences.USER_ID, 999999)
        val token = Preferences.getString(Preferences.USER_TOKEN, "")
        val call = feedTypeService.getFeedTypesList(token, userId)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                if (response.isSuccessful) {
                    try {
                        val remoteResponse: String = response.body()!!.string()
                        val jsonArray = JSONArray(remoteResponse)
                        feedTypes = FeedTypesJSONConverter()
                            .deserializeList(jsonArray)
                        onFinish.invoke(feedTypes)
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
                onFinish.invoke(feedTypes)
                call.cancel()
            }
        })
    }

    fun getFeedTypeByIDFromServer(
        onStart: () -> Unit,
        onFinish: (ArrayList<FeedType>) -> Unit,
        context: Context,
        soughtFeedTypeID: Int
    ) {
        onStart.invoke()
        var feedTypes = ArrayList<FeedType>()
        val feedTypeService =
            getQueryClient(context).create(FeedTypeService::class.java)
        val userId = Preferences.getInt(Preferences.USER_ID, 99999999)
        val token = Preferences.getString(Preferences.USER_TOKEN, "")
        val call = feedTypeService.getFeedTypeByID(token, userId, soughtFeedTypeID)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                if (response.isSuccessful) {
                    try {
                        val remoteResponse: String = response.body()!!.string()
                        val jsonArray = JSONArray(remoteResponse)
                        feedTypes = FeedTypesJSONConverter()
                            .deserializeList(jsonArray)
                        onFinish.invoke(feedTypes)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                        onFinish.invoke(feedTypes)
                    }

                } else {
                    Log.e("error", response.errorBody()!!.string())
                    onFinish.invoke(feedTypes)
                }

            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("fail", t.message + t.localizedMessage)
                ToastMaker().toastErrorConnect(context)
                onFinish.invoke(feedTypes)
                call.cancel()
            }
        })
    }

    fun editFeedTypeByID(
        onStart: () -> Unit,
        onFinish: () -> Unit,
        context: Context,
        feedType: FeedType
    ) {
        onStart.invoke()
        val feedTypeService =
            getQueryClient(context).create(FeedTypeService::class.java)
        val userId = Preferences.getInt(Preferences.USER_ID, 9999999)
        val token = Preferences.getString(Preferences.USER_TOKEN, "")
        val call = feedTypeService.editFeedType(
            token,
            userId,
            feedType.title,
            feedType.id)
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

    fun addFeedType(
        onStart: () -> Unit,
        onFinish: () -> Unit,
        context: Context,
        feedType: FeedType
    ) {
        onStart.invoke()
        val feedTypeService =
            getQueryClient(context).create(FeedTypeService::class.java)
        val userId = Preferences.getInt(Preferences.USER_ID, 999999)
        val token = Preferences.getString(Preferences.USER_TOKEN, "")
        val call = feedTypeService.addFeedType(
            token,
            userId,
            feedType.title
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
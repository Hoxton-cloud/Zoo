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
import ru.zoo.data.converters.FeedsJSONConverter
import ru.zoo.data.models.Feed
import ru.zoo.db.getQueryClient
import ru.zoo.db.services.FeedService
import ru.zoo.extensions.view.ToastMaker

class FeedsQueries {
    fun getFeedsFromServer(
        onStart: () -> Unit,
        onFinish: (ArrayList<Feed>) -> Unit,
        context: Context
    ) {
        onStart.invoke()
        var feeds = ArrayList<Feed>()
        val feedService =
            getQueryClient(context).create(FeedService::class.java)
        val userId = Preferences.getInt(Preferences.USER_ID, 999999)
        val token = Preferences.getString(Preferences.USER_TOKEN, "")
        val call = feedService.getFeedsList(token, userId)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                if (response.isSuccessful) {
                    try {
                        val remoteResponse: String = response.body()!!.string()
                        val jsonArray = JSONArray(remoteResponse)
                        feeds = FeedsJSONConverter()
                            .deserializeList(jsonArray)
                        onFinish.invoke(feeds)
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
                onFinish.invoke(feeds)
                call.cancel()
            }
        })
    }

    fun getFeedByIDFromServer(
        onStart: () -> Unit,
        onFinish: (ArrayList<Feed>) -> Unit,
        context: Context,
        soughtFeedID: Int
    ) {
        onStart.invoke()
        var feeds = ArrayList<Feed>()
        val feedService =
            getQueryClient(context).create(FeedService::class.java)
        val userId = Preferences.getInt(Preferences.USER_ID, 99999999)
        val token = Preferences.getString(Preferences.USER_TOKEN, "")
        val call = feedService.getFeedByID(token, userId, soughtFeedID)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                if (response.isSuccessful) {
                    try {
                        val remoteResponse: String = response.body()!!.string()
                        val jsonArray = JSONArray(remoteResponse)
                        feeds = FeedsJSONConverter()
                            .deserializeList(jsonArray)
                        onFinish.invoke(feeds)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                        onFinish.invoke(feeds)
                    }

                } else {
                    Log.e("error", response.errorBody()!!.string())
                    onFinish.invoke(feeds)
                }

            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("fail", t.message + t.localizedMessage)
                ToastMaker().toastErrorConnect(context)
                onFinish.invoke(feeds)
                call.cancel()
            }
        })
    }

    fun editFeedByID(
        onStart: () -> Unit,
        onFinish: () -> Unit,
        context: Context,
        feed: Feed
    ) {
        onStart.invoke()
        val feedService =
            getQueryClient(context).create(FeedService::class.java)
        val userId = Preferences.getInt(Preferences.USER_ID, 9999999)
        val token = Preferences.getString(Preferences.USER_TOKEN, "")
        val call = feedService.editFeed(
            token,
            userId,
            feed.title,
            feed.typeID,
            feed.id)
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

    fun addFeed(
        onStart: () -> Unit,
        onFinish: () -> Unit,
        context: Context,
        feed: Feed
    ) {
        onStart.invoke()
        val feedService =
            getQueryClient(context).create(FeedService::class.java)
        val userId = Preferences.getInt(Preferences.USER_ID, 999999)
        val token = Preferences.getString(Preferences.USER_TOKEN, "")
        val call = feedService.addFeed(
            token,
            userId,
            feed.title,
            feed.typeID,
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
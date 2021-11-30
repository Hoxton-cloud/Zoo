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
import ru.zoo.data.converters.UsersJSONConverter
import ru.zoo.data.models.User
import ru.zoo.db.getQueryClient
import ru.zoo.db.services.UserService
import ru.zoo.extensions.view.ToastMaker

class UsersQueries {
    fun getUsersFromServer(
        onStart: () -> Unit,
        onFinish: (ArrayList<User>) -> Unit,
        context: Context
    ) {
        onStart.invoke()
        var users = ArrayList<User>()
        val userService =
            getQueryClient(context).create(UserService::class.java)
        val userId = Preferences.getString(Preferences.USER_ID, "")
        val token = Preferences.getString(Preferences.USER_TOKEN, "")
        val call = userService.getUsersList(token, userId)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                if (response.isSuccessful) {
                    try {
                        val remoteResponse: String = response.body()!!.string()
                        val jsonArray = JSONArray(remoteResponse)
                        users = UsersJSONConverter()
                            .deserializeList(jsonArray)
                        onFinish.invoke(users)
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
                onFinish.invoke(users)
                call.cancel()
            }
        })
    }

    fun getUserByIDFromServer(
        onStart: () -> Unit,
        onFinish: (ArrayList<User>) -> Unit,
        context: Context,
        soughtUserID: Int
    ) {
        onStart.invoke()
        var users = ArrayList<User>()
        val userService =
            getQueryClient(context).create(UserService::class.java)
        val userId = Preferences.getString(Preferences.USER_ID, "")
        val token = Preferences.getString(Preferences.USER_TOKEN, "")
        val call = userService.getUserByID(token, userId, soughtUserID.toString())
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                if (response.isSuccessful) {
                    try {
                        val remoteResponse: String = response.body()!!.string()
                        val jsonArray = JSONArray(remoteResponse)
                        users = UsersJSONConverter()
                            .deserializeList(jsonArray)
                        onFinish.invoke(users)
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
                onFinish.invoke(users)
                call.cancel()
            }
        })
    }

    fun editUserByID(
        onStart: () -> Unit,
        onFinish: () -> Unit,
        context: Context,
        user: User
    ) {
        onStart.invoke()
        val userService =
            getQueryClient(context).create(UserService::class.java)
        val userId = Preferences.getString(Preferences.USER_ID, "")
        val token = Preferences.getString(Preferences.USER_TOKEN, "")
        val call = userService.editUser(token, userId, user.username, user.password, user.employeeID.toString(), user.role)
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

    fun addUser(
        onStart: () -> Unit,
        onFinish: () -> Unit,
        context: Context,
        user: User
    ) {
        onStart.invoke()
        val userService =
            getQueryClient(context).create(UserService::class.java)
        val userId = Preferences.getString(Preferences.USER_ID, "")
        val token = Preferences.getString(Preferences.USER_TOKEN, "")
        val call = userService.addUser(token, userId, user.username, user.password, user.employeeID.toString(), user.role)
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
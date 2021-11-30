package ru.zoo.db.services

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginService {
//    @FormUrlEncoded
    @POST("/login")
    fun login(
    @Query("username") username: String?, @Query("password") password: String?
    ): Call<ResponseBody>
}
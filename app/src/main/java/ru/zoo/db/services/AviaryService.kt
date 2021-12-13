package ru.zoo.db.services

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AviaryService {
    @GET("/mobileGetAviariesList")
    fun getAviariesList(
        @Query("token") token: String?, @Query("userID") userID: String?
    ): Call<ResponseBody>

    @GET("/mobileGetAviaryByID")
    fun getAviaryByID(
        @Query("token") token: String?, @Query("userID") userID: String?, @Query("aviaryID") aviaryID: String?
    ): Call<ResponseBody>

    @POST("/mobileEditAviary/add")
    fun addAviary(
        @Query("token") token: String?,
        @Query("userID") userID: String?,
        @Query("number") number: String?,
        @Query("typeID") typeID: String?
    ): Call<ResponseBody>

    @POST("/mobileEditAviary/update")
    fun editAviary(
        @Query("token") token: String?,
        @Query("userID") userID: String?,
        @Query("number") number: String?,
        @Query("typeID") typeID: String?,
        @Query("id") id: String?
    ): Call<ResponseBody>
}
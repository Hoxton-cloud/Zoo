package ru.zoo.db.services

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AviaryTypeService {
    @GET("/mobileGetAviaryTypesList")
    fun getAviaryTypesList(
        @Query("token") token: String?, @Query("userID") userID: String?
    ): Call<ResponseBody>

    @GET("/mobileGetAviaryTypeByID")
    fun getAviaryTypeByID(
        @Query("token") token: String?, @Query("userID") userID: String?, @Query("aviaryTypeID") aviaryTypeID: String?
    ): Call<ResponseBody>

    @POST("/mobileEditAviaryType/add")
    fun addAviaryType(
        @Query("token") token: String?,
        @Query("userID") userID: String?,
        @Query("title") title: String?
    ): Call<ResponseBody>

    @POST("/mobileEditAviaryType/update")
    fun editAviaryType(
        @Query("token") token: String?,
        @Query("userID") userID: String?,
        @Query("title") title: String?,
        @Query("id") id: String?
    ): Call<ResponseBody>
}
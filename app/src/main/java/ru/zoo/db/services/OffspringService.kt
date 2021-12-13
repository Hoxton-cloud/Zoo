package ru.zoo.db.services

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface OffspringService {
    @GET("/mobileGetOffspringsList")
    fun getOffspringsList(
        @Query("token") token: String?, @Query("userID") userID: String?
    ): Call<ResponseBody>

    @GET("/mobileGetOffspringByID")
    fun getOffspringByID(
        @Query("token") token: String?, @Query("userID") userID: String?, @Query("offspringID") offspringID: String?
    ): Call<ResponseBody>

    @POST("/mobileEditOffspring/add")
    fun addOffspring(
        @Query("token") token: String?,
        @Query("userID") userID: String?,
        @Query("amount") amount: String?,
        @Query("animalID") animalID: String?
    ): Call<ResponseBody>

    @POST("/mobileEditOffspring/update")
    fun editOffspring(
        @Query("token") token: String?,
        @Query("userID") userID: String?,
        @Query("amount") amount: String?,
        @Query("animalID") animalID: String?,
        @Query("id") id: String?
    ): Call<ResponseBody>
}
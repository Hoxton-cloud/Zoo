package ru.zoo.db.services

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RationService {
    @GET("/mobileGetRationsList")
    fun getRationsList(
        @Query("token") token: String?, @Query("userID") userID: Int?
    ): Call<ResponseBody>

    @GET("/mobileGetRationByID")
    fun getRationByID(
        @Query("token") token: String?, @Query("userID") userID: Int?, @Query("rationID") rationID: Int?
    ): Call<ResponseBody>

    @POST("/mobileEditRation/add")
    fun addRation(
        @Query("token") token: String?,
        @Query("userID") userID: Int?,
        @Query("feedID") feedID: Int?,
        @Query("animalID") animalID: Int?,
        @Query("time") time: String?,
        @Query("mass") mass: String?
    ): Call<ResponseBody>

    @POST("/mobileEditRation/update")
    fun editRation(
        @Query("token") token: String?,
        @Query("userID") userID: Int?,
        @Query("feedID") feedID: Int?,
        @Query("animalID") animalID: Int?,
        @Query("time") time: String?,
        @Query("mass") mass: String?,
        @Query("id") id: Int?
    ): Call<ResponseBody>
}
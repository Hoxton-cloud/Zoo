package ru.zoo.db.services

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PositionService {
    @GET("/mobileGetPositionsList")
    fun getPositionsList(
        @Query("token") token: String?, @Query("userID") userID: Int?
    ): Call<ResponseBody>

    @GET("/mobileGetPositionByID")
    fun getPositionByID(
        @Query("token") token: String?, @Query("userID") userID: Int?, @Query("positionID") positionID: Int?
    ): Call<ResponseBody>

    @POST("/mobileEditPosition/add")
    fun addPosition(
        @Query("token") token: String?,
        @Query("userID") userID: Int?,
        @Query("title") date: String?,
        @Query("salary") animalID: String?
    ): Call<ResponseBody>

    @POST("/mobileEditPosition/update")
    fun editPosition(
        @Query("token") token: String?,
        @Query("userID") userID: Int?,
        @Query("title") date: String?,
        @Query("salary") animalID: String?,
        @Query("id") id: Int?
    ): Call<ResponseBody>
}
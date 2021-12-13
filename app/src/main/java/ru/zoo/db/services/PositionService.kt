package ru.zoo.db.services

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PositionService {
    @GET("/mobileGetPositionsList")
    fun getPositionsList(
        @Query("token") token: String?, @Query("userID") userID: String?
    ): Call<ResponseBody>

    @GET("/mobileGetPositionByID")
    fun getPositionByID(
        @Query("token") token: String?, @Query("userID") userID: String?, @Query("positionID") positionID: String?
    ): Call<ResponseBody>

    @POST("/mobileEditPosition/add")
    fun addPosition(
        @Query("token") token: String?,
        @Query("userID") userID: String?,
        @Query("date") date: String?,
        @Query("animalID") animalID: String?,
        @Query("diagnosisID") diagnosisID: String?
    ): Call<ResponseBody>

    @POST("/mobileEditPosition/update")
    fun editPosition(
        @Query("token") token: String?,
        @Query("userID") userID: String?,
        @Query("date") date: String?,
        @Query("animalID") animalID: String?,
        @Query("diagnosisID") diagnosisID: String?,
        @Query("id") id: String?
    ): Call<ResponseBody>
}
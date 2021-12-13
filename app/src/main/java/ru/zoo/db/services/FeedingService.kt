package ru.zoo.db.services

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface FeedingService {
    @GET("/mobileGetFeedingsList")
    fun getFeedingsList(
        @Query("token") token: String?, @Query("userID") userID: String?
    ): Call<ResponseBody>

    @GET("/mobileGetFeedingByID")
    fun getFeedingByID(
        @Query("token") token: String?, @Query("userID") userID: String?, @Query("feedingID") feedingID: String?
    ): Call<ResponseBody>

    @POST("/mobileEditFeeding/add")
    fun addFeeding(
        @Query("token") token: String?,
        @Query("userID") userID: String?,
        @Query("date") date: String?,
        @Query("animalID") animalID: String?,
        @Query("employeeID") employeeID: String?,
        @Query("aviaryID") aviaryID: String?
    ): Call<ResponseBody>

    @POST("/mobileEditFeeding/update")
    fun editFeeding(
        @Query("token") token: String?,
        @Query("userID") userID: String?,
        @Query("title") title: String?,
        @Query("id") id: String?
    ): Call<ResponseBody>
}
package ru.zoo.db.services

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CheckinService {
    @GET("/mobileGetCheckinsList")
    fun getCheckinsList(
        @Query("token") token: String?, @Query("userID") userID: String?
    ): Call<ResponseBody>

    @GET("/mobileGetCheckinByID")
    fun getCheckinByID(
        @Query("token") token: String?, @Query("userID") userID: String?, @Query("checkinID") checkinID: String?
    ): Call<ResponseBody>

    @POST("/mobileEditCheckin/add")
    fun addCheckin(
        @Query("token") token: String?,
        @Query("userID") userID: String?,
        @Query("date") date: String?,
        @Query("animalID") animalID: String?,
        @Query("employeeID") employeeID: String?,
        @Query("aviaryID") aviaryID: String?
    ): Call<ResponseBody>

    @POST("/mobileEditCheckin/update")
    fun editCheckin(
        @Query("token") token: String?,
        @Query("userID") userID: String?,
        @Query("date") date: String?,
        @Query("animalID") animalID: String?,
        @Query("employeeID") employeeID: String?,
        @Query("aviaryID") aviaryID: String?,
        @Query("id") id: String?
    ): Call<ResponseBody>
}
package ru.zoo.db.services

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ClimateZoneService {
    @GET("/mobileGetClimateZonesList")
    fun getClimateZonesList(
        @Query("token") token: String?, @Query("userID") userID: String?
    ): Call<ResponseBody>

    @GET("/mobileGetClimateZoneByID")
    fun getClimateZoneByID(
        @Query("token") token: String?, @Query("userID") userID: String?, @Query("climateZoneID") climateZoneID: String?
    ): Call<ResponseBody>

    @POST("/mobileEditClimateZone/add")
    fun addClimateZone(
        @Query("token") token: String?,
        @Query("userID") userID: String?,
        @Query("title") title: String?
    ): Call<ResponseBody>

    @POST("/mobileEditClimateZone/update")
    fun editClimateZone(
        @Query("token") token: String?,
        @Query("userID") userID: String?,
        @Query("title") title: String?,
        @Query("id") id: String?
    ): Call<ResponseBody>
}
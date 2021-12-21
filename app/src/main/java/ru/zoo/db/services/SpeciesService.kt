package ru.zoo.db.services

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface SpeciesService {
    @GET("/mobileGetSpeciesList")
    fun getSpeciesList(
        @Query("token") token: String?, @Query("userID") userID: String?
    ): Call<ResponseBody>

    @GET("/mobileGetSpeciesByID")
    fun getSpeciesByID(
        @Query("token") token: String?, @Query("userID") userID: Int?, @Query("speciesID") speciesID: Int?
    ): Call<ResponseBody>

    @POST("/mobileEditSpecies/add")
    fun addSpecies(
        @Query("token") token: String?,
        @Query("userID") userID: Int?,
        @Query("title") title: String?,
        @Query("climateZoneID") climateZoneID: Int?,
        @Query("typeID") typeID: Int?
    ): Call<ResponseBody>

    @POST("/mobileEditSpecies/update")
    fun editSpecies(
        @Query("token") token: String?,
        @Query("userID") userID: Int?,
        @Query("title") title: String?,
        @Query("climateZoneID") climateZoneID: Int?,
        @Query("typeID") typeID: Int?,
        @Query("id") id: String?
    ): Call<ResponseBody>
}
package ru.zoo.db.services

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AnimalTypeService {
    @GET("/mobileGetAnimalTypesList")
    fun getAnimalTypesList(
        @Query("token") token: String?, @Query("userID") userID: String?
    ): Call<ResponseBody>

    @GET("/mobileGetAnimalTypeByID")
    fun getAnimalTypeByID(
        @Query("token") token: String?, @Query("userID") userID: String?, @Query("animalTypeID") animalTypeID: String?
    ): Call<ResponseBody>

    @POST("/mobileEditAnimalType/add")
    fun addAnimalType(
        @Query("token") token: String?,
        @Query("userID") userID: String?,
        @Query("title") title: String?
    ): Call<ResponseBody>

    @POST("/mobileEditAnimalType/update")
    fun editAnimalType(
        @Query("token") token: String?,
        @Query("userID") userID: String?,
        @Query("title") title: String?,
        @Query("id") id: String?
    ): Call<ResponseBody>
}
package ru.zoo.db.services

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AnimalService {
    @GET("/mobileGetAnimalsList")
    fun getAnimalsList(
        @Query("token") token: String?, @Query("userID") userID: Int?
    ): Call<ResponseBody>

    @GET("/mobileGetAnimalByID")
    fun getAnimalByID(
        @Query("token") token: String?, @Query("userID") userID: Int?, @Query("animalID") animalID: Int?
    ): Call<ResponseBody>

    @POST("/mobileEditAnimal/add")
    fun addAnimal(
        @Query("token") token: String?,
        @Query("userID") userID: Int?,
        @Query("name") name: String?,
        @Query("sex") sex: String?,
        @Query("speciesID") speciesID: Int?
    ): Call<ResponseBody>

    @POST("/mobileEditAnimals/update")
    fun editAnimal(
        @Query("token") token: String?,
        @Query("userID") userID: Int?,
        @Query("name") name: String?,
        @Query("sex") sex: String?,
        @Query("speciesID") speciesID: Int?,
        @Query("id") id: Int?
    ): Call<ResponseBody>
}
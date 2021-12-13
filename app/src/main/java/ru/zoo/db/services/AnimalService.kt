package ru.zoo.db.services

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AnimalService {
    @GET("/mobileGetAnimalsList")
    fun getAnimalsList(
        @Query("token") token: String?, @Query("userID") userID: String?
    ): Call<ResponseBody>

    @GET("/mobileGetAnimalByID")
    fun getAnimalByID(
        @Query("token") token: String?, @Query("userID") userID: String?, @Query("animalID") animalID: String?
    ): Call<ResponseBody>

    @POST("/mobileEditAnimal/add")
    fun addAnimal(
        @Query("token") token: String?,
        @Query("userID") userID: String?,
        @Query("name") name: String?,
        @Query("sex") sex: String?,
        @Query("speciesID") speciesID: String?
    ): Call<ResponseBody>

    @POST("/mobileEditAnimals/update")
    fun editAnimal(
        @Query("token") token: String?,
        @Query("userID") userID: String?,
        @Query("name") name: String?,
        @Query("sex") sex: String?,
        @Query("speciesID") speciesID: String?,
        @Query("id") id: String?
    ): Call<ResponseBody>
}
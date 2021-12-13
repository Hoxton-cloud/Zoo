package ru.zoo.db.services

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface SupplierService {
    @GET("/mobileGetSuppliersList")
    fun getSuppliersList(
        @Query("token") token: String?, @Query("userID") userID: String?
    ): Call<ResponseBody>

    @GET("/mobileGetSupplierByID")
    fun getSupplierByID(
        @Query("token") token: String?, @Query("userID") userID: String?, @Query("supplierID") supplierID: String?
    ): Call<ResponseBody>

    @POST("/mobileEditSupplier/add")
    fun addSupplier(
        @Query("token") token: String?,
        @Query("userID") userID: String?,
        @Query("amount") amount: String?,
        @Query("animalID") animalID: String?
    ): Call<ResponseBody>

    @POST("/mobileEditSupplier/update")
    fun editSupplier(
        @Query("token") token: String?,
        @Query("userID") userID: String?,
        @Query("amount") amount: String?,
        @Query("animalID") animalID: String?,
        @Query("id") id: String?
    ): Call<ResponseBody>
}
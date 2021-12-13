package ru.zoo.db.services

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface FeedSupplyService {
    @GET("/mobileGetFeedSuppliesList")
    fun getFeedSuppliesList(
        @Query("token") token: String?, @Query("userID") userID: String?
    ): Call<ResponseBody>

    @GET("/mobileGetFeedSupplyByID")
    fun getFeedSupplyByID(
        @Query("token") token: String?, @Query("userID") userID: String?, @Query("feedSupplyID") feedSupplyID: String?
    ): Call<ResponseBody>

    @POST("/mobileEditFeedSupply/add")
    fun addFeedSupply(
        @Query("token") token: String?,
        @Query("userID") userID: String?,
        @Query("quantity") quantity: String?,
        @Query("price") price: String?,
        @Query("feedID") feedID: String?,
        @Query("supplierID") supplierID: String?
    ): Call<ResponseBody>

    @POST("/mobileEditFeedSupply/update")
    fun editFeedSupply(
        @Query("token") token: String?,
        @Query("userID") userID: String?,
        @Query("quantity") quantity: String?,
        @Query("price") price: String?,
        @Query("feedID") feedID: String?,
        @Query("supplierID") supplierID: String?,
        @Query("id") id: String?
    ): Call<ResponseBody>
}
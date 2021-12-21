package ru.zoo.db.services

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface FeedTypeService {
    @GET("/mobileGetFeedTypesList")
    fun getFeedTypesList(
        @Query("token") token: String?, @Query("userID") userID: Int?
    ): Call<ResponseBody>

    @GET("/mobileGetFeedTypeByID")
    fun getFeedTypeByID(
        @Query("token") token: String?, @Query("userID") userID: Int?, @Query("feedTypeID") feedTypeID: Int?
    ): Call<ResponseBody>

    @POST("/mobileEditFeedType/add")
    fun addFeedType(
        @Query("token") token: String?,
        @Query("userID") userID: Int?,
        @Query("title") title: String?
    ): Call<ResponseBody>

    @POST("/mobileEditFeedType/update")
    fun editFeedType(
        @Query("token") token: String?,
        @Query("userID") userID: Int?,
        @Query("title") title: String?,
        @Query("id") id: Int?
    ): Call<ResponseBody>
}
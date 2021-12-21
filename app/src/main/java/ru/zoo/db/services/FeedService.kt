package ru.zoo.db.services

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface FeedService {
    @GET("/mobileGetFeedsList")
    fun getFeedsList(
        @Query("token") token: String?, @Query("userID") userID: Int?
    ): Call<ResponseBody>

    @GET("/mobileGetFeedByID")
    fun getFeedByID(
        @Query("token") token: String?, @Query("userID") userID: Int?, @Query("feedID") feedID: Int?
    ): Call<ResponseBody>

    @POST("/mobileEditFeed/add")
    fun addFeed(
        @Query("token") token: String?,
        @Query("userID") userID: Int?,
        @Query("title") title: String?,
        @Query("typeID") typeID: Int?
    ): Call<ResponseBody>

    @POST("/mobileEditFeed/update")
    fun editFeed(
        @Query("token") token: String?,
        @Query("userID") userID: Int?,
        @Query("title") title: String?,
        @Query("typeID") typeID: Int?,
        @Query("id") id: Int?
    ): Call<ResponseBody>
}
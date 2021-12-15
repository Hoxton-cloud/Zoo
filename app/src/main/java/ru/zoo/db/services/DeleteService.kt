package ru.zoo.db.services

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface DeleteService {
    @DELETE("/deleteAll")
    fun deleteAll(
    @Query("token") token: String?, @Query("userID") userID: Int?, @Query("table") table: String): Call<ResponseBody>

    @DELETE("/deleteIdItem")
    fun deleteIdItem(
        @Query("token") token: String?, @Query("userID") userID: Int?, @Query("table") table: String, @Query("id") id: Int): Call<ResponseBody>
}
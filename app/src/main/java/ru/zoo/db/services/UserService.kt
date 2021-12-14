package ru.zoo.db.services

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserService {
    @GET("/mobileGetUsersList")
    fun getUsersList(
        @Query("token") token: String?, @Query("userID") userID: Int?
    ): Call<ResponseBody>

    @GET("/mobileGetUserByID")
    fun getUserByID(
        @Query("token") token: String?, @Query("userID") userID: Int?, @Query("soughtUserID") soughtUserID: Int?
    ): Call<ResponseBody>

    @POST("/mobileEditUser/add")
    fun addUser(
        @Query("token") token: String?,
        @Query("userID") userID: Int?,
        @Query("username") username: String?,
        @Query("password") password: String?,
        @Query("employeeID") employeeID: Int?,
        @Query("role") role: String?
    ): Call<ResponseBody>

    @POST("/mobileEditUser/update")
    fun editUser(
        @Query("token") token: String?,
        @Query("userID") userID: Int?,
        @Query("username") username: String?,
        @Query("password") password: String?,
        @Query("employeeID") employeeID: Int?,
        @Query("role") role: String?,
        @Query("id") id: Int?
    ): Call<ResponseBody>
}
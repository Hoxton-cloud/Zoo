//package ru.zoo.db.services
//
//import okhttp3.ResponseBody
//import retrofit2.Call
//import retrofit2.http.GET
//import retrofit2.http.POST
//import retrofit2.http.Query
//
//interface SpeciesService {
//    @GET("/mobileGetSpeciesList")
//    fun getSpeciesList(
//        @Query("token") token: String?, @Query("userID") userID: String?
//    ): Call<ResponseBody>
//
//    @GET("/mobileGetRationByID")
//    fun getRationByID(
//        @Query("token") token: String?, @Query("userID") userID: String?, @Query("rationID") rationID: String?
//    ): Call<ResponseBody>
//
//    @POST("/mobileEditRation/add")
//    fun addRation(
//        @Query("token") token: String?,
//        @Query("userID") userID: String?,
//        @Query("feedID") feedID: String?,
//        @Query("animalID") animalID: String?,
//        @Query("time") time: String?,
//        @Query("mass") mass: String?
//    ): Call<ResponseBody>
//
//    @POST("/mobileEditRation/update")
//    fun editRation(
//        @Query("token") token: String?,
//        @Query("userID") userID: String?,
//        @Query("feedID") feedID: String?,
//        @Query("animalID") animalID: String?,
//        @Query("time") time: String?,
//        @Query("mass") mass: String?,
//        @Query("id") id: String?
//    ): Call<ResponseBody>
//}
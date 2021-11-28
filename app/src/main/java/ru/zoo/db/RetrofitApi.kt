package ru.zoo.db

import android.content.Context
import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import ru.zoo.data.Constants
import java.io.IOException


fun getLoginClient(): Retrofit {
    val httpClient =
        OkHttpClient.Builder().addInterceptor(object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val original = chain.request()
                val request =
                    original.newBuilder()
                        .method(original.method, original.body)
                        .build()
                Log.d("Клиент", request.toString())
                return chain.proceed(request)
            }
        })
    val client = httpClient.build()
    return Retrofit.Builder()
        .baseUrl(Constants.url)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
}

fun getQueryClient(context: Context?): Retrofit {
    val httpClient =
        OkHttpClient.Builder().addInterceptor(object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val original = chain.request()
                val request = original.newBuilder()
                    .method(original.method, original.body)
                    .build()
                Log.d("Клиент", request.toString())
                return chain.proceed(request)
            }
        })
    val client = httpClient.build()
    return Retrofit.Builder()
        .baseUrl(Constants.url)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
}
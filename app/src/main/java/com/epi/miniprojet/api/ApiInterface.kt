package com.epi.miniprojet.api

import com.epi.miniprojet.models.User
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiInterface {
    @Headers("Content-Type:application/json")
    @GET("/photos/random?")
    fun getImages(
        @Query("client_id") clientId: String,
        @Query("count") count: String
    ): Call<ResponseBody>

    class RetrofitInstance {
        companion object {
            val BASE_URL: String =
                "https://api.unsplash.com"
            val interceptor: HttpLoggingInterceptor =
                HttpLoggingInterceptor().apply {
                    this.level = HttpLoggingInterceptor.Level.BODY
                }
            val client: OkHttpClient = OkHttpClient.Builder().apply {
                this.addInterceptor(interceptor)
            }.build()

            fun getRetrofitInstance(): Retrofit {
                return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
        }
    }
}
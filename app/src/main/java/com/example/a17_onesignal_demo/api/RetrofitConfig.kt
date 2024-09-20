package com.example.a17_onesignal_demo.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Constance {
    const val ONE_URL = "https://onesignal.com"
}


object RetrofitConfig {

    private
    const val BASE_URL = Constance.ONE_URL

    val interactor = Interceptor { chain ->
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
            .addHeader("Authorization", "Bearer YzM3ZDE4MjQtOGY5NC00NTRiLTkzNzktNTM2YjA0NWQyNTAy")
            .addHeader("Content-Type", "application/json")
            .build()
        chain.proceed(newRequest)
    }


    val client = OkHttpClient.Builder().retryOnConnectionFailure(true)
        .addInterceptor(interactor)
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()


    val api: ArticleService by lazy {
        val retofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retofit.create(ArticleService::class.java)
    }
}
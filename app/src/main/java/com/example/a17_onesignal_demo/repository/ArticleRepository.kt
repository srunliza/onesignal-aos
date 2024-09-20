package com.example.a17_onesignal_demo.repository

import com.example.a17_onesignal_demo.api.RequestNotification
import com.example.a17_onesignal_demo.api.RetrofitConfig
import retrofit2.Response

class ArticleRepository {
    val api = RetrofitConfig.api

    suspend fun postNotification(request: RequestNotification): Response<Void> {
        return api.postNotification(request)
    }

}
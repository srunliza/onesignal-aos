package com.example.a17_onesignal_demo.api

import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface ArticleService {

    @POST("/api/v1/notifications")
    suspend fun postNotification(@Body request: RequestNotification = RequestNotification()): Response<Void>

}

data class RequestNotification(
    @SerializedName("app_id") val app_id: String = "5d445358-8543-4720-a954-821765488291",
    @SerializedName("headings") val headings: Heading = Heading(),
    @SerializedName("contents") val content: Content = Content(),
    @SerializedName("include_player_ids") val include_player_ids: List<String> = listOf("fcc5e783-7acc-4fda-b128-eca4c8dc8d8d")
)

class Heading {
    @SerializedName("en")
    var en: String? = "Hello Friend"
}

class Content {
    @SerializedName("en")
    var en: String? = "This is a test notification"
}





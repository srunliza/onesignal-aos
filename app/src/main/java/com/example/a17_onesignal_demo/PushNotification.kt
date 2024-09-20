package com.example.a17_onesignal_demo

import android.util.Log
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okio.IOException
import org.json.JSONArray
import org.json.JSONObject


fun sendPushNotification(subscriptionIds: List<String>, title: String, message: String) {
    val appId = "5d445358-8543-4720-a954-821765488291"
    val restApiKey = "YzM3ZDE4MjQtOGY5NC00NTRiLTkzNzktNTM2YjA0NWQyNTAy"
    // Create OkHttpClient
    val client = OkHttpClient()
    // Create the JSON request body
    val jsonBody = JSONObject().apply {
        put("app_id", appId)
        put("headings", JSONObject().put("en", title))
        put("contents", JSONObject().put("en", message))
        put("include_subscription_ids", JSONArray(subscriptionIds))
    }


    val body = jsonBody.toString().toRequestBody(
        "application/json; charset=utf-8".toMediaTypeOrNull()
    )

    // Build the request
    val request = Request.Builder()
        .url("https://api.onesignal.com/notifications")
        .post(body)
        .addHeader("Content-Type", "application/json; charset=utf-8")
        .addHeader("Authorization", "Basic $restApiKey") // Add your OneSignal REST API key
        .build()
    Log.d("Request notification", "Request: $request")

    // Execute the request
    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            Log.d("Response notification", "Failed to send notification: ${e.message}")
        }

        override fun onResponse(call: Call, response: Response) {
            if (response.isSuccessful) {
                Log.d(
                    "Response notification",
                    "Notification sent successfully: ${response.code} - ${response.body?.string()}"
                )
            } else {
                Log.d(
                    "Response notification",
                    "Failed to send notification. Error: ${response.code} - ${response.body?.string()}"
                )
            }
        }
    })
}


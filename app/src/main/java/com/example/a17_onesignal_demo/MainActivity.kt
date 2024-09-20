package com.example.a17_onesignal_demo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.a17_onesignal_demo.ui.theme._17_OneSignal_DemoTheme
import com.onesignal.OneSignal
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


class MainActivity : ComponentActivity() {

    private val user1 = "b23d2532-950e-46d8-8614-b8a261bf8cc3"
    private val user2 = "fcc5e783-7acc-4fda-b128-eca4c8dc8d8d"
    private val deviceState = OneSignal.User.pushSubscription.id


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _17_OneSignal_DemoTheme {
                SendNotification(listOf(user2))
            }
        }
        Log.d("Device State", "Device State: $deviceState")
    }
}


@Composable
fun SendNotification(targetPlayerId: List<String>) {
    Column(
        modifier = Modifier.fillMaxSize().padding(top = 18.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                sendPushNotification(targetPlayerId, "Hello Friend", "This is a test notification")
            }
        ) {
            Text(text = "Send Notification to User")
        }
    }
}



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




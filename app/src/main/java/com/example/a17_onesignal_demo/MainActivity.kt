package com.example.a17_onesignal_demo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import com.example.a17_onesignal_demo.api.RequestNotification
import com.example.a17_onesignal_demo.ui.theme._17_OneSignal_DemoTheme
import com.example.a17_onesignal_demo.viewmodel.ArticleViewModel
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

//    private val user1 = "b23d2532-950e-46d8-8614-b8a261bf8cc3"
//    private val user2 = "fcc5e783-7acc-4fda-b128-eca4c8dc8d8d"

    // for track user device state
    private val deviceState = OneSignal.User.pushSubscription.id
    private val viewModel: ArticleViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _17_OneSignal_DemoTheme {
                SendNotification(articleViewModel = viewModel)
            }
        }


        Log.d("Device State", "Device State: $deviceState")
    }
}


@Composable
fun SendNotification(articleViewModel: ArticleViewModel = ArticleViewModel()) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 18.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                articleViewModel.postNotification(request = RequestNotification())
            }
        ) {
            Text(text = "Send Notification to User")
        }
    }
}










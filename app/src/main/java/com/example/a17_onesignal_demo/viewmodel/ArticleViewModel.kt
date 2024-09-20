package com.example.a17_onesignal_demo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a17_onesignal_demo.api.RequestNotification
import com.example.a17_onesignal_demo.repository.ArticleRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class ArticleViewModel : ViewModel() {
    private val articleRepository = ArticleRepository()


    fun postNotification(request: RequestNotification) {
        viewModelScope.launch {
            articleRepository.postNotification(request)
        }
    }


}
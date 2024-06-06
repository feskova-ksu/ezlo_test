package com.example.ezlotest.data

import com.example.ezlotest.data.model.ItemsResponse
import retrofit2.http.GET

interface ApiService {
    @GET("test_android/items.test")
    suspend fun getItems(): ItemsResponse
}

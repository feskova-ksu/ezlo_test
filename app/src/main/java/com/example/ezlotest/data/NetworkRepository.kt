package com.example.ezlotest.data

import com.example.ezlotest.data.model.ItemsResponse
import javax.inject.Inject

interface INetworkRepository {
    suspend fun getItem():ItemsResponse
}
class NetworkRepository @Inject constructor(private val apiService: ApiService):INetworkRepository {
    override suspend fun getItem(): ItemsResponse {
        return apiService.getItems()
    }
}

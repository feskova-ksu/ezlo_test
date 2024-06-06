package com.example.ezlotest.data

import com.example.ezlotest.data.model.DeviceItem
import com.example.ezlotest.data.model.mapForDB
import javax.inject.Inject

interface INetworkRepository {
    suspend fun getItems(): List<DeviceItem>
}

class NetworkRepository @Inject constructor(private val apiService: ApiService) :
    INetworkRepository {
    override suspend fun getItems(): List<DeviceItem> {
        val list = apiService.getItems().devices
        return list.map { it.mapForDB(list.indexOf(it) + 1) }
    }
}

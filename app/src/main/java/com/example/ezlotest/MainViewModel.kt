package com.example.ezlotest

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ezlotest.data.IDatabaseRepository
import com.example.ezlotest.data.INetworkRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val network: INetworkRepository,
    private val database: IDatabaseRepository
) : ViewModel() {

    fun getAndSaveItems() {
        viewModelScope.launch {
            val res = network.getItems()
            Log.e("MainViewModel", "res = ${res}")
            database.saveAllItems(res)
            getAllDevicesFromDB()
        }
    }

    private fun getAllDevicesFromDB() {
        viewModelScope.launch {
            val res = database.getAllDevices()
            Log.e("MainViewModel", "from db res = ${res}")

        }
    }
}

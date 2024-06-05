package com.example.ezlotest

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ezlotest.data.INetworkRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: INetworkRepository) : ViewModel() {

    fun getItems() {
        viewModelScope.launch {
            val res = repository.getItem()
            Log.e("MainViewModel", "res = ${res.devices}")
        }
    }
}

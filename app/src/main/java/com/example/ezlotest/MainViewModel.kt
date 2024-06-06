package com.example.ezlotest

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ezlotest.data.IDatabaseRepository
import com.example.ezlotest.data.INetworkRepository
import com.example.ezlotest.data.model.DevicePreview
import com.example.ezlotest.data.model.mapToDevicePreview
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val network: INetworkRepository,
    private val database: IDatabaseRepository
) : ViewModel() {


    private val _uiState = MutableStateFlow(MainUIState())
    val uiState = _uiState.asStateFlow()

    init {
        getAllDevicesFromDB()
    }

    var selectedPkDevice: Int? = null

    fun reset() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update {
                it.copy(dialogVisible = true)
            }
            getAndSaveItems()
        }
    }

    fun onDeviceLongPress(pkDevice: Int) {
        selectedPkDevice = pkDevice
        _uiState.update {
            it.copy(dialogVisible = true)
        }
    }

    fun onDeleteDevice() {
        viewModelScope.launch(Dispatchers.IO) {
            selectedPkDevice?.let {
                database.deleteByPK(it)
            }
        }
    }

    private fun getAllDevicesFromDB() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { it.copy(isLoading = true) }
            database.getAllDevices().collect { devices ->
                if (devices.isEmpty()) {
                    getAndSaveItems()
                } else {
                    val res = devices.map { it.mapToDevicePreview() }
                    _uiState.update {
                        it.copy(devices = res, isLoading = false)
                    }
                }
            }
        }
    }

    private suspend fun getAndSaveItems() {
        val res = network.getItems()
        Log.e("MainViewModel", "res = $res")
        database.saveAllItems(res)
    }
}


data class MainUIState(
    val name: String = "",
    val profileImage: Int = R.drawable.ic_launcher_background,
    val devices: List<DevicePreview> = emptyList(),
    val isLoading: Boolean = false,
    val dialogVisible: Boolean = false
)


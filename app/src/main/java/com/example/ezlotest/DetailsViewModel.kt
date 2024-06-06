package com.example.ezlotest

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ezlotest.data.IDatabaseRepository
import com.example.ezlotest.data.model.DeviceDetail
import com.example.ezlotest.data.model.mapToDeviceDetail
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = DetailsViewModel.DetailViewModelFactory::class)
class DetailsViewModel @AssistedInject constructor(
    @Assisted private val deviceId: Int,
    private val database: IDatabaseRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailUIState())
    val uiState: StateFlow<DetailUIState> = _uiState.asStateFlow()

    init {
        getData()
    }

    fun saveNewTitle(newTitle: String) {
        if (newTitle == uiState.value.device?.title) {
            _uiState.update {
                it.copy(canGoBack = true)
            }
        } else
            viewModelScope.launch(Dispatchers.IO) {
                uiState.value.device?.let {
                    database.getDevice(it.pkDevice).collect {
                        database.updateObject(it.copy(title = newTitle))
                        _uiState.update {
                            it.copy(canGoBack = true)
                        }
                    }
                }
            }
    }

    private fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update {
                it.copy(isLoading = true)
            }
            val device = database.getDevice(deviceId).first()
            Log.e("getData", "Res = $device")
            val info = device.mapToDeviceDetail()
            _uiState.update {
                it.copy(device = info, isLoading = false)
            }
        }
    }

    @AssistedFactory
    interface DetailViewModelFactory {
        fun create(deviceId: Int): DetailsViewModel
    }
}

data class DetailUIState(
    val isLoading: Boolean = false,
    val device: DeviceDetail? = null,
    val canGoBack: Boolean = false
)
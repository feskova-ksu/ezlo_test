package com.example.ezlotest.data.model

import com.google.gson.annotations.SerializedName

data class ItemsResponse(
    @SerializedName("Devices")
    val devices: List<Device>
)

package com.example.ezlotest.ui.model

data class DeviceDetail(
    val pkDevice: Int,
    val title: String,
    val macAddress: String,
    val firmware: String,
    val imageId: Int,
    val model: String
)
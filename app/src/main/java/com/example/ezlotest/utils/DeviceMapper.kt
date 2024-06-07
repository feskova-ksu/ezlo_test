package com.example.ezlotest.utils

import com.example.ezlotest.data.model.Device
import com.example.ezlotest.data.model.DeviceItem
import com.example.ezlotest.ui.model.DeviceDetail
import com.example.ezlotest.ui.model.DevicePreview

const val TITLE_PREFIX = "Home Number"
fun DeviceItem.mapToDevicePreview() = DevicePreview(
    pkDevice = pkDevice,
    title = title,
    imageId = deviceInfo.platform.getImageFromPlatform()
)

fun DeviceItem.mapToDeviceDetail() = DeviceDetail(
    pkDevice = pkDevice,
    title = title,
    macAddress = deviceInfo.macAddress,
    firmware = deviceInfo.firmware,
    imageId = deviceInfo.platform.getImageFromPlatform(),
    model = deviceInfo.platform.getModelFromPlatform()
)

fun Device.mapForDB(number: Int) = DeviceItem(
    pkDevice = this.pkDevice,
    title = "$TITLE_PREFIX $number",
    deviceInfo = this
)
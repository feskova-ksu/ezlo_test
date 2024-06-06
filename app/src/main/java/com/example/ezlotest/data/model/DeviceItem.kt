package com.example.ezlotest.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson

@Entity(tableName = "DeviceDB")
data class DeviceItem(
    @PrimaryKey
    val pkDevice: Int,
    val title: String,
    val deviceInfo: Device = Device()
)

data class DevicePreview(
    val pkDevice: Int,
    val title: String,
    val platform:String
)

data class DeviceDetail(
    val pkDevice: Int,
    val title: String,
    val macAddress: String,
    val firmware: String,
    val platform: String
)

class Converters {
    @TypeConverter
    fun fromCustomType(customType: Device): String {
        return Gson().toJson(customType)
    }

    @TypeConverter
    fun toCustomType(data: String): Device {
        return Gson().fromJson(data, Device::class.java)
    }
}

fun DeviceItem.mapToDevicePreview() = DevicePreview(
    pkDevice = pkDevice,
    title = title,
    platform = deviceInfo.platform
)

fun DeviceItem.mapToDeviceDetail() = DeviceDetail(
    pkDevice = pkDevice,
    title = title,
    macAddress = deviceInfo.macAddress,
    firmware = deviceInfo.firmware,
    platform = deviceInfo.platform
)
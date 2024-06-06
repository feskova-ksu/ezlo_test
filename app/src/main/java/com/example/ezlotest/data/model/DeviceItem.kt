package com.example.ezlotest.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.ezlotest.R
import com.google.gson.Gson

@Entity(tableName = "DeviceDB")
data class DeviceItem(
    @PrimaryKey
    val pkDevice: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "deviceInfo")
    val deviceInfo: Device = Device()
)

data class DevicePreview(
    val pkDevice: Int,
    val title: String,
    val imageId: Int
)

data class DeviceDetail(
    val pkDevice: Int,
    val title: String,
    val macAddress: String,
    val firmware: String,
    val imageId: Int,
    val model: String
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

fun String.getImageFromPlatform(): Int {
    return when (this) {
        "Sercomm G450" -> R.drawable.vera_plus_big
        "Sercomm G550" -> R.drawable.vera_secure_big
        "MiCasaVerde VeraLite" -> R.drawable.vera_edge_big
        "Sercomm NA900" -> R.drawable.vera_edge_big
        "Sercomm NA301" -> R.drawable.vera_edge_big
        "Sercomm NA930" -> R.drawable.vera_edge_big
        else -> R.drawable.vera_edge_big
    }
}

fun String.getModelFromPlatform(): String {
    return when (this) {
        "Sercomm G450" -> "Vera Plus"
        "Sercomm G550" -> "Vera Secure"
        "MiCasaVerde VeraLite" -> "Vera Edge"
        "Sercomm NA900" -> "Vera Edge"
        "Sercomm NA301" -> "Vera Edge"
        "Sercomm NA930" -> "Vera Edge"
        else -> "Vera Edge"
    }
}
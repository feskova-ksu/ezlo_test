package com.example.ezlotest.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "DeviceDB")
data class DeviceItem(
    @PrimaryKey
    val pkDevice: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "deviceInfo")
    val deviceInfo: Device = Device()
)

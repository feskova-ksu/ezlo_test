package com.example.ezlotest.data.db

import androidx.room.TypeConverter
import com.example.ezlotest.data.model.Device
import com.google.gson.Gson

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
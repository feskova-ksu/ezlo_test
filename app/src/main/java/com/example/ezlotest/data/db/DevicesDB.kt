package com.example.ezlotest.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.ezlotest.data.model.DeviceItem

@Database(entities = [DeviceItem::class], version = 1)
@TypeConverters(Converters::class)
abstract class DevicesDB : RoomDatabase() {

    abstract fun getDevicesDAO() : DevicesDAO

}
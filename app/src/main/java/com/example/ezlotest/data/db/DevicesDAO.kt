package com.example.ezlotest.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.ezlotest.data.model.DeviceItem

@Dao
interface DevicesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllDevices(devices: List<DeviceItem>)

    @Query("SELECT * FROM DeviceDB")
    suspend fun getAllDevices(): List<DeviceItem>

    @Query("DELETE FROM DeviceDB  WHERE pkDevice = :pkDevice")
    suspend fun deleteByPK(pkDevice: Int)

    @Query("SELECT * FROM DeviceDB WHERE pkDevice = :pkDevice LIMIT 1")
    suspend fun getDeviceByPK(pkDevice: Int): DeviceItem

    @Update
    suspend fun updateObject(entity: DeviceItem)
}
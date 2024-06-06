package com.example.ezlotest.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.ezlotest.data.model.DeviceItem
import kotlinx.coroutines.flow.Flow

@Dao
interface DevicesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllDevices(devices: List<DeviceItem>)

    @Query("SELECT * FROM DeviceDB")
    fun getAllDevices(): Flow<List<DeviceItem>>

    @Query("DELETE FROM DeviceDB  WHERE pkDevice = :pkDevice")
    fun deleteByPK(pkDevice: Int)

    @Query("SELECT * FROM DeviceDB WHERE pkDevice = :pkDevice LIMIT 1")
    fun getDeviceByPK(pkDevice: Int): Flow<DeviceItem>

    @Update
    fun updateObject(entity: DeviceItem)
}
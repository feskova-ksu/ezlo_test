package com.example.ezlotest.data

import com.example.ezlotest.data.db.DevicesDB
import com.example.ezlotest.data.model.DeviceItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface IDatabaseRepository {
    suspend fun saveAllItems(items: List<DeviceItem>)
    suspend fun getAllDevices(): Flow<List<DeviceItem>>
    suspend fun deleteByPK(pkDevice: Int)
    suspend fun getDevice(pk: Int): Flow<DeviceItem>
    suspend fun updateObject(entity: DeviceItem)
}

class DatabaseRepository @Inject constructor(database: DevicesDB) :
    IDatabaseRepository {

    private val dao = database.getDevicesDAO()
    override suspend fun saveAllItems(items: List<DeviceItem>) {
        dao.saveAllDevices(items)
    }

    override suspend fun getAllDevices(): Flow<List<DeviceItem>> {
        return dao.getAllDevices()
    }

    override suspend fun deleteByPK(pkDevice: Int) {
        dao.deleteByPK(pkDevice)
    }

    override suspend fun getDevice(pk: Int): Flow<DeviceItem> {
        return dao.getDeviceByPK(pk)
    }

    override suspend fun updateObject(entity: DeviceItem) {
        dao.updateObject(entity)
    }
}

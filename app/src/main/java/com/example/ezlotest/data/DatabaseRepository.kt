package com.example.ezlotest.data

import com.example.ezlotest.data.db.DevicesDB
import com.example.ezlotest.data.model.DeviceItem
import javax.inject.Inject

interface IDatabaseRepository {
    suspend fun saveAllItems(items: List<DeviceItem>)
    suspend fun getAllDevices(): List<DeviceItem>
    suspend fun deleteByPK(pkDevice: Int)
    suspend fun getDevice(pk: Int): DeviceItem
    suspend fun updateObject(entity: DeviceItem)
}

class DatabaseRepository @Inject constructor(database: DevicesDB) :
    IDatabaseRepository {

    private val dao = database.getDevicesDAO()
    override suspend fun saveAllItems(items: List<DeviceItem>) {
        dao.saveAllDevices(items)
    }

    override suspend fun getAllDevices(): List<DeviceItem> {
        return dao.getAllDevices()
    }

    override suspend fun deleteByPK(pkDevice: Int) {
        dao.deleteByPK(pkDevice)
    }

    override suspend fun getDevice(pk: Int): DeviceItem {
        return dao.getDeviceByPK(pk)
    }

    override suspend fun updateObject(entity: DeviceItem) {
        dao.updateObject(entity)
    }
}

package com.example.hydration

import kotlinx.coroutines.flow.Flow

class WaterRepository(private val waterDao: WaterDao) { //Might talk to multiple DAO

    suspend fun insert(record: WaterRecord) {
        waterDao.insertWaterRecord(record)
    }

    suspend fun update(record: WaterRecord) {
        waterDao.updateWaterRecord(record)
    }

    suspend fun delete(record: WaterRecord) {
        waterDao.deleteWaterRecord(record)
    }

    fun getRecordForDay(day: String): Flow<WaterRecord> {
        return waterDao.getRecordForDay(day)
    }

    fun getAllRecords(): Flow<List<WaterRecord>> {
        return waterDao.getAllRecords()
    }

}
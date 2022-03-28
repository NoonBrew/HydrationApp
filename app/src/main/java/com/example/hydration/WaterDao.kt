package com.example.hydration

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao // data access object
interface WaterDao { // Defines Queries and relates SQL functions to our activity

    // onConflict is used to avoid an error is the same primary key is used for multiple records
    // This will replace the current row with a given primary key with a new row of data.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWaterRecord(record: WaterRecord)

    @Update // Room will create and automatically generate the SQL code for Insert, Update, Delete.
    suspend fun updateWaterRecord(record: WaterRecord)

    @Delete
    suspend fun deleteWaterRecord(record: WaterRecord)

    @Query("SELECT * FROM WaterRecord WHERE day = :day LIMIT 1")
    fun getRecordForDay(day: String): Flow<WaterRecord>

    @Query("SELECT * FROM WaterRecord") // Flow allows us to not use suspend.
    fun getAllRecords(): Flow<List<WaterRecord>> // Notifies the activity when data is ready.
}
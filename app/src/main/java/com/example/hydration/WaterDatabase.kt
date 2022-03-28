package com.example.hydration

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [WaterRecord::class], version = 1)
abstract class WaterDatabase: RoomDatabase() {

    abstract fun waterDao(): WaterDao // Kotlin implements this function and creates code for these functions

    companion object {

        // Volatile and Synchronized help make sure only one thread can access this code at a time.
        @Volatile
        private var INSTANCE: WaterDatabase? = null
        // Singleton function. Helps make sure we do not make multiple copies of our database.
        fun getDatabase(context: Context): WaterDatabase {
            // Elvis operator checks if there is an instance of the WaterDatabase,
            // If there is it will return that instance if not it will build the database.
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context, // Context of database.
                    WaterDatabase::class.java, // Database class that has the DAO
                "water-database").build() // Database name
                INSTANCE = instance // we then set the INSTANCE variable to the instance of the database
                // And return the database.
                return instance
            }
        }
    }
}
package com.example.hydration

import android.app.Application

class HydrationApplication: Application() {

    private val database by lazy { WaterDatabase.getDatabase(this) }

    // Activity can access this repository
    val repository by lazy { WaterRepository(database.waterDao()) }
}
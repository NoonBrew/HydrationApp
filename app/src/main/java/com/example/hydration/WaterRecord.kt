package com.example.hydration

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class WaterRecord(
    @PrimaryKey
    @NonNull
    val day: String,
    @NonNull
    glasses: Int) {


    companion object{
        const val MAX_GLASSES = 5
    }

    // Sets the value glasses using a custom setter
    var glasses: Int = glasses
        set(value) {
            // If the int value passed to WaterRecord is not within the allowed range an
            // error is thrown so it doesn't create an improper record.
            if (value < 0 || value > MAX_GLASSES) {
                throw IllegalArgumentException("Glasses must be between 0 and $MAX_GLASSES")
            }
            field = value
        }
    // Have to define our own to string function.
    override fun toString(): String {
        return "Day=$day, Glasses=$glasses"
    }
}

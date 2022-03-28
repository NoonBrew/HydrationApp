package com.example.hydration

import java.text.DateFormatSymbols
import java.util.*

class DaysRepository {

    val weekdays: List<String>
        get(){
            // Gets the date symbols for the devices default language.
            val dateFormatSymbols = DateFormatSymbols.getInstance(Locale.getDefault())
            /**
             * DataFormatSymbols provides an 8 element array, the first element is blank
             * { , Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday }
             * This is so the days can be numbered starting at 1. To not cause issues with
             * other code we return the array removing blank elements.
             */
            return dateFormatSymbols.weekdays.asList().filter { it.isNotBlank() }
        }

    val todayIndex: Int
        get() {
            // Returns 0-based index of the day
            val today = Calendar.getInstance(Locale.getDefault())
            // Returns a number, not a name, starting with 1. First day of the week is 1.
            val day = today.get(Calendar.DAY_OF_WEEK) // Calendar is from the device.
            return day - 1 // To convert to 0 Base.
        }
}
package com.example.hydration

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class WaterViewModel(private val waterRepository: WaterRepository,
                     private val daysRepository: DaysRepository): ViewModel() {

    val allRecords = waterRepository.getAllRecords().asLiveData()

    fun insertNewRecord(record: WaterRecord) {
        viewModelScope.launch { // Allows for a background function to be called by a non-background function
            waterRepository.insert(record)
        }
    }

    fun updateRecord(record: WaterRecord) {
        viewModelScope.launch {
            waterRepository.update(record)
        }
    }

    fun deleteRecord(record: WaterRecord) {
        viewModelScope.launch {
            waterRepository.delete(record)
        }
    }

    fun getRecordForDay(day: String): LiveData<WaterRecord> {
        return waterRepository.getRecordForDay(day).asLiveData()
    }

    fun getWeekdays() :List<String> {
        return daysRepository.weekdays
    }

    fun getTodayIndex(): Int {
        return daysRepository.todayIndex
    }


}

// Class called by ViewModel provider to make view model objects.
class WaterViewModelFactory(private val waterRepository: WaterRepository,
                            private val daysRepository: DaysRepository): ViewModelProvider.Factory {
    override fun <viewModel : ViewModel> create(modelClass: Class<viewModel>): viewModel {
        if (modelClass.isAssignableFrom(WaterViewModel::class.java)) {
            return WaterViewModel(waterRepository, daysRepository) as viewModel
        }
        throw IllegalArgumentException("$modelClass is not a WaterViewModel")
    }

}
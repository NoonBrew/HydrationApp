package com.example.hydration

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class WaterViewModel(private val repository: WaterRepository): ViewModel() {

    val allRecords = repository.getAllRecords().asLiveData()

    fun insertNewRecord(record: WaterRecord) {
        viewModelScope.launch { // Allows for a background function to be called by a non-background function
            repository.insert(record)
        }
    }

    fun updateRecord(record: WaterRecord) {
        viewModelScope.launch {
            repository.update(record)
        }
    }

    fun deleteRecord(record: WaterRecord) {
        viewModelScope.launch {
            repository.delete(record)
        }
    }

    fun getRecordForDay(day: String): LiveData<WaterRecord> {
        return repository.getRecordForDay(day).asLiveData()
    }


}

// Class called by ViewModel provider to make view model objects.
class WaterViewModelFactory(private val repository: WaterRepository): ViewModelProvider.Factory {
    override fun <viewModel : ViewModel> create(modelClass: Class<viewModel>): viewModel {
        if (modelClass.isAssignableFrom(WaterViewModel::class.java)) {
            return WaterViewModel(repository) as viewModel
        }
        throw IllegalArgumentException("$modelClass is not a WaterViewModel")
    }

}
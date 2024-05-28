package com.example.avicenna_diagnostics.ui.reading

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.avicenna_diagnostics.backend.Database

class ReadingViewModel : ViewModel() {

    private val _readingAtSixPointFive = MutableLiveData<Float>()
    val readingAtSixPointFive: LiveData<Float> get() = _readingAtSixPointFive

    init {
        fetchReadingAtSixPointFive()
    }

    private fun fetchReadingAtSixPointFive() {
        val reading = Database.glucoseData.find { it.first == 6.5f }?.second
        _readingAtSixPointFive.value = reading ?: 0f
    }
}


//class AdvancedViewModel : ViewModel() {
//
//    private val _readingAtSixPointFive = MutableLiveData<Float>()
////    private val _text = MutableLiveData<String>().apply {
////        value = "Avicenna Statistics information goes here"
////    }
////    val text: LiveData<String> = _text
//    val readingAtSixPointFive: LiveData<Float> get() = _readingAtSixPointFive
//
//    init {
//        fetchReadingAtSixPointFive()
//    }
//
//    private fun fetchReadingAtSixPointFive() {
//        val reading = Database.glucoseData.find { it.first == 6.5f }?.second
//        _readingAtSixPointFive.value = reading ?: 0f
//    }
//}


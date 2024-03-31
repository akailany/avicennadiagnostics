package com.example.avicenna_diagnostics.ui.statistics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StatisticsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Avicenna Statistics information goes here"
    }
    val text: LiveData<String> = _text
}
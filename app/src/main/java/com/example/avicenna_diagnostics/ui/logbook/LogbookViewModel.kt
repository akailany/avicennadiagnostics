package com.example.avicenna_diagnostics.ui.logbook

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LogbookViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Avicenna Statistics information goes here"
    }
    val text: LiveData<String> = _text
}
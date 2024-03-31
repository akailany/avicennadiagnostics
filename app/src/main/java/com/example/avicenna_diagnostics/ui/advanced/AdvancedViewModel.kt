package com.example.avicenna_diagnostics.ui.advanced

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AdvancedViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Avicenna Advanced Tab, what goes here is TBD"
    }
    val text: LiveData<String> = _text
}
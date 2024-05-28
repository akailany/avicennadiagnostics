package com.example.avicenna_diagnostics.ui.glucometer

//Import Database

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.avicenna_diagnostics.backend.Database
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GlucometerViewModel : ViewModel() {

    private val _glucoseLevels = MutableStateFlow<Pair<Float, Float>?>(null)
    val glucoseLevels: StateFlow<Pair<Float, Float>?> = _glucoseLevels

    private val _flag = MutableLiveData<Boolean>(true)
    private var flag = false // Regular Boolean for internal logic

    fun setFlag(value: Boolean) {
        _flag.value = value
        flag = value
    }

    private val _text = MutableLiveData<String>().apply {
        value = "Collect blood sample then press below button"
    }
    val text: LiveData<String> = _text

    private var currentIndex = 0

    init {
        Log.d("GlucometerViewModel", "Class initialized with currentIndex: $currentIndex")
    }

    fun getNextDataPoint() {
        if ((currentIndex < Database.glucoseData.size) && flag) {
            _glucoseLevels.value = Database.glucoseData[currentIndex]
            Log.d("DataPoint", "Current Index: $currentIndex, Data Point: ${_glucoseLevels.value}")
            currentIndex++
        } else {
            // Reset or handle the end of the dataset
            currentIndex = 0
            setFlag(false)
            Log.d("DataPoint", "Else Index: $currentIndex")
        }
    }
}

/******************************************
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GlucometerViewModel : ViewModel() {

    private val _glucoseLevels = MutableStateFlow<Pair<Float, Float>?>(null)
    val glucoseLevels: StateFlow<Pair<Float, Float>?> = _glucoseLevels

    private val _flag = MutableLiveData<Boolean>(true)
    private var flag = false // Regular Boolean for internal logic

    fun setFlag(value: Boolean) {
        _flag.value = value
        flag = value
    }


    private val _text = MutableLiveData<String>().apply {
        value = "Collect blood sample then press below button"
    }
    val text: LiveData<String> = _text

    private val dataset = listOf(
        0f to 0.314998f,
        0.1f to 0.3048004887f,
        0.2f to 0.2974487688f,
        0.3f to 0.2877407526f,
        0.4f to 0.2772893645f,
        0.5f to 0.2823154555f,
        0.6f to 0.2891725608f,
        0.7f to 0.2920304531f,
        0.8f to 0.3025415408f,
        0.9f to 0.3115583265f,
        1f to 0.3322965793f,
        1.1f to 0.3537053316f,
        1.2f to 0.3648025303f,
        1.3f to 0.3766076288f,
        1.4f to 0.3855530602f,
        1.5f to 0.3755553732f,
        1.6f to 0.3637491906f,
        1.7f to 0.3258970166f,
        1.8f to 0.2800564802f,
        1.9f to 0.2522718911f,
        2f to 0.2291287565f,
        2.1f to 0.2046358088f,
        2.2f to 0.1897680741f,
        2.3f to 0.18294396f,
        2.4f to 0.1820232831f,
        2.5f to 0.1810307349f,
        2.6f to 0.1762967736f,
        2.7f to 0.1769406833f,
        2.8f to 0.1718442764f,
        2.9f to 0.1652019895f,
        3f to 0.1603871416f,
        3.1f to 0.1573460875f,
        3.2f to 0.1567553924f,
        3.3f to 0.1532812705f,
        3.4f to 0.1530414113f,
        3.5f to 0.1539788673f,
        3.6f to 0.1533175485f,
        3.7f to 0.1443564799f,
        3.8f to 0.1337243831f,
        3.9f to 0.1371559917f,
        4f to 0.1523096376f,
        4.1f to 0.1699780314f,
        4.2f to 0.1954308869f,
        4.3f to 0.2234192357f,
        4.4f to 0.2551798042f,
        4.5f to 0.2753971673f,
        4.6f to 0.276920961f,
        4.7f to 0.2766800679f,
        4.8f to 0.2805176141f,
        4.9f to 0.2792637564f,
        5f to 0.2804678664f,
        5.1f to 0.2791826489f,
        5.2f to 0.280145952f,
        5.3f to 0.2841064917f,
        5.4f to 0.2856443983f,
        5.5f to 0.2870534193f,
        5.6f to 0.2867252903f,
        5.7f to 0.284920762f,
        5.8f to 0.2834950259f,
        5.9f to 0.2789817911f,
        6.0f to 0.2738651089f,
        6.1f to 0.2686918571f,
        6.2f to 0.262505578f,
        6.3f to 0.259962243f,
        6.4f to 0.253332327f,
        6.5f to 0.2442546491f,
        6.6f to 0.2412852883f,
        6.7f to 0.2314043874f,
        6.8f to 0.2256331485f,
        6.9f to 0.2247548388f,
        7.0f to 0.2293679388f,
        7.1f to 0.2372406904f,
        7.2f to 0.2437914817f,
        7.3f to 0.2457064581f,
        7.4f to 0.25256085f,
        7.5f to 0.2492055781f,
        7.6f to 0.2441315541f,
        7.7f to 0.2406080152f,
        7.8f to 0.2400184645f,
        7.9f to 0.2383072953f,
        8.0f to 0.2353459723f,
        8.1f to 0.2331621395f,
        8.2f to 0.2296710182f,
        8.3f to 0.2264084345f,
        8.4f to 0.2244593741f,
        8.5f to 0.2226815813f,
        8.6f to 0.2223969248f,
        8.7f to 0.2219858908f,
        8.8f to 0.2219709564f,
        8.9f to 0.2242313171f,
        9.0f to 0.2263804224f,
        9.1f to 0.2316090569f,
        9.2f to 0.237184126f,
        9.3f to 0.2420197515f,
        9.4f to 0.2500368447f,
        9.5f to 0.2528865037f,
        9.6f to 0.2512494123f,
        9.7f to 0.2552859926f,
        9.8f to 0.2560341917f,
        9.9f to 0.2578572814f,
        10.0f to 0.2703177586f
    )

    private var currentIndex = 0

    init {
        // This block allows execution of code at instance initialization
        Log.d("YourClass", "Class initialized with currentIndex: $currentIndex")
    }

    fun getNextDataPoint() {
        if ((currentIndex < dataset.size) && flag) {
            _glucoseLevels.value = dataset[currentIndex]
            currentIndex++
            Log.d("DataPoint", "Current Index: $currentIndex") // Add this line to log the current index

        } else {
            // Reset or handle the end of the dataset
            currentIndex = 0
            setFlag(false);
            _glucoseLevels.value = null
            Log.d("DataPoint", "Else Index: $currentIndex") // Add this line to log the current index

        }
    }
}
******************************************/
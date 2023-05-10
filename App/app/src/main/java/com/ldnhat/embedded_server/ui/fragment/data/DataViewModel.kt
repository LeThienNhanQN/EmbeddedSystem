package com.ldnhat.embedded_server.ui.fragment.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ldnhat.embedded_server.common.model.Data
import com.ldnhat.embedded_server.enums.ApiStatus
import com.ldnhat.embedded_server.network.DataApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.sql.Time
import java.util.*
import kotlin.collections.ArrayList

class DataViewModel : ViewModel() {
    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _status = MutableLiveData<ApiStatus.State>()

    val status: LiveData<ApiStatus.State>
        get() = _status

    private var _response = MutableLiveData<String>()

    val response: LiveData<String>
        get() = _response

    private var _data = MutableLiveData<List<Data>>()

    val data: LiveData<List<Data>>
        get() = _data

    private var _dataCurrent = MutableLiveData<Data>()

    val dataCurrent: LiveData<Data>
        get() = _dataCurrent

    private var _temperatureValue = MutableLiveData<String>()

    val temperatureValue: LiveData<String>
        get() = _temperatureValue

    private var _humidityValue = MutableLiveData<String>()

    val humidityValue: LiveData<String>
        get() = _humidityValue

    private var _soilValue = MutableLiveData<String>()

    val soilValue: LiveData<String>
        get() = _soilValue

    init {
        userGetAllData()
        val timerTask = object : TimerTask() {
            override fun run() {
                userGetCurrentData()
            }
        }
        Timer().schedule(timerTask, 0, 2000)
    }

    private fun userGetCurrentData() {
        try {
            coroutineScope.launch {
                val getDataCurrentDeferred = DataApi.dataApiService.userGetDataCurrentAsync()

                val dataCurrent = getDataCurrentDeferred.await()

                _dataCurrent.value = dataCurrent.data
            }
        } catch (e: Exception) {
            _response.value = "Failure: ${e.message}"
            _dataCurrent.value = null
        }
    }

    private fun userGetAllData() {
        try {
            coroutineScope.launch {
                val getAllDataDeferred =
                    DataApi.dataApiService.userGetAllDataAsync()
                _status.value = ApiStatus.State.LOADING

                val result = getAllDataDeferred.await()
                _status.value = ApiStatus.State.DONE

                _data.value = result.data
            }
        } catch (e: Exception) {
            _response.value = "Failure: ${e.message}"
            _status.value = ApiStatus.State.ERROR
            _data.value = ArrayList()
        }
    }

    fun setTemperatureValue(temperatureValue: String) {
        _temperatureValue.value = temperatureValue
    }

    fun setHumidityValue(humidityValue: String) {
        _humidityValue.value = humidityValue
    }

    fun setSoilValue(soilValue: String) {
        _soilValue.value = soilValue
    }

    override fun onCleared() {
        super.onCleared()
        println("viewModel cleared")
        viewModelJob.cancel()
    }
}
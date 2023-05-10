package com.ldnhat.embedded_server.ui.fragment.chart

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

class ChartViewModel : ViewModel() {
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

    init {
        userGetAllData()
    }

    private fun userGetAllData() {
        try {
            coroutineScope.launch {
                val getAllDataDeferred =
                    DataApi.dataApiService.userGetAllDataAsync()
                _status.value = ApiStatus.State.LOADING

                val result = getAllDataDeferred.await()
                _status.value = ApiStatus.State.DONE
//                Log.d("data", result.data.toString())
                _data.value = result.data
            }
        } catch (e: Exception) {
            _response.value = "Failure: ${e.message}"
            _status.value = ApiStatus.State.ERROR
            _data.value = ArrayList()
        }
    }

    override fun onCleared() {
        super.onCleared()
        println("viewModel cleared")
        viewModelJob.cancel()
    }
}
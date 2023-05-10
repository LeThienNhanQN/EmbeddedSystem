package com.ldnhat.embedded_server.ui.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class HomeViewModel : ViewModel() {
    private val _dataButtonClick = MutableLiveData<Boolean>()

    val dataButtonClick: LiveData<Boolean>
        get() = _dataButtonClick

    private val _chartButtonClick = MutableLiveData<Boolean>()

    val chartButtonClick: LiveData<Boolean>
        get() = _chartButtonClick

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    fun onDataButtonClick() {
        _dataButtonClick.value = true
    }

    fun onDataButtonClickCompleted() {
        _dataButtonClick.value = false
    }

    fun onChartButtonClick() {
        _chartButtonClick.value = true
    }

    fun onChartButtonClickCompleted() {
        _chartButtonClick.value = false
    }


    override fun onCleared() {
        super.onCleared()
        println("viewModel cleared")
        viewModelJob.cancel()
    }
}
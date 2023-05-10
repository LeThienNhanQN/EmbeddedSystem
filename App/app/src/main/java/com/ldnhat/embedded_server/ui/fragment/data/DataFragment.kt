package com.ldnhat.embedded_server.ui.fragment.data

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ldnhat.embedded_server.R
import com.ldnhat.embedded_server.databinding.FragmentDataBinding
import com.ldnhat.embedded_server.utils.constants.Constants

class DataFragment : Fragment() {
    private val dataViewModel : DataViewModel by lazy {
        ViewModelProvider(this).get(DataViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentDataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_data, container, false)
        binding.dataViewModel = dataViewModel

        binding.rvDataList.adapter = DataAdapter()

        dataViewModel.dataCurrent.observe(viewLifecycleOwner, {
            if (null != it) {
                dataViewModel.setHumidityValue(it.humidity + Constants.SPACE + it.humidityMeasure)
                dataViewModel.setTemperatureValue(it.temperature + Constants.SPACE + it.temperatureMeasure)
                dataViewModel.setSoilValue(it.soilMoisture + Constants.SPACE + it.soilMoistureMeasure)
            }
        })

        binding.lifecycleOwner = this
        return binding.root
    }
}
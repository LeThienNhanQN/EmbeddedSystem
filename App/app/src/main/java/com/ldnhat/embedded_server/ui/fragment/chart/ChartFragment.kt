package com.ldnhat.embedded_server.ui.fragment.chart

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.ldnhat.embedded_server.R
import com.ldnhat.embedded_server.common.model.Data
import com.ldnhat.embedded_server.databinding.FragmentChartBinding

class ChartFragment : Fragment() {
    internal var data: List<Data> = ArrayList()

    private val chartViewModel by lazy {
        ViewModelProvider(this).get(ChartViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentChartBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_chart, container, false)
        binding.chartViewModel = chartViewModel

        chartViewModel.data.observe(viewLifecycleOwner, {
            if (null != it) {
                data = it.filter { s ->
                            isNumeric(s.humidity) &&
                            isNumeric(s.temperature) &&
                            isNumeric(s.soilMoisture)
                }
                generateTemperatureChart(data, binding)
                generateHumidityChart(data, binding)
                generateSoilChart(data, binding)
            }
        })

        binding.lifecycleOwner = this
        return binding.root
    }

    private fun generateTemperatureChart(data: List<Data>, binding: FragmentChartBinding) {
        val temperatureChart = binding.temperatureChart
        temperatureChart.description.isEnabled = false
        temperatureChart.setDrawGridBackground(false)
        temperatureChart.setDrawBarShadow(false)
        temperatureChart.isHighlightFullBarEnabled = false

        val rightAxis: YAxis = temperatureChart.axisRight
        rightAxis.setDrawGridLines(false)
        rightAxis.axisMinimum = -100f

        val leftAxis: YAxis = temperatureChart.axisLeft
        leftAxis.setDrawGridLines(false)
        leftAxis.axisMinimum = -100f

        val xAxis: XAxis = temperatureChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.mAxisMinimum = 0f
        xAxis.granularity = 1f

        val combinedData = CombinedData()
        val lineData = LineData()
        lineData.addDataSet(dataTemperatureChart(data) as ILineDataSet)
        combinedData.setData(lineData)
        xAxis.axisMaximum = combinedData.xMax + 0.25f
        temperatureChart.data = combinedData
        temperatureChart.invalidate()
    }

    private fun dataTemperatureChart(data: List<Data>): DataSet<Entry> {
        val lineData = LineData()

        val entries = ArrayList<Entry>()
        for (i in data.indices) {
            entries.add(Entry(i.toFloat(), (data[i].temperature).toFloat()))
        }

        val set = LineDataSet(entries, "Temperature data")
        set.color = Color.RED
        set.lineWidth = 2.5f
        set.setCircleColor(Color.RED)
        set.circleRadius = 2f
        set.fillColor = Color.RED
        set.mode = LineDataSet.Mode.CUBIC_BEZIER
        set.setDrawValues(true)
        set.valueTextSize = 7f
        set.valueTextColor = Color.RED

        set.axisDependency = YAxis.AxisDependency.LEFT

        set.axisDependency = YAxis.AxisDependency.LEFT
        lineData.addDataSet(set)

        return set
    }

    private fun generateHumidityChart(data: List<Data>, binding: FragmentChartBinding) {
        val humidityChart = binding.humidityChart
        humidityChart.description.isEnabled = false
        humidityChart.setDrawGridBackground(false)
        humidityChart.setDrawBarShadow(false)
        humidityChart.isHighlightFullBarEnabled = false

        val rightAxis: YAxis = humidityChart.axisRight
        rightAxis.setDrawGridLines(false)
        rightAxis.axisMinimum = -100f

        val leftAxis: YAxis = humidityChart.axisLeft
        leftAxis.setDrawGridLines(false)
        leftAxis.axisMinimum = -100f

        val xAxis: XAxis = humidityChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.mAxisMinimum = 0f
        xAxis.granularity = 1f

        val combinedData = CombinedData()
        val lineData = LineData()
        lineData.addDataSet(dataHumidityChart(data) as ILineDataSet)
        combinedData.setData(lineData)
        xAxis.axisMaximum = combinedData.xMax + 0.25f
        humidityChart.data = combinedData
        humidityChart.invalidate()
    }

    private fun dataHumidityChart(data: List<Data>): DataSet<Entry> {
        val lineData = LineData()

        val entries = ArrayList<Entry>()
        for (i in data.indices) {
            entries.add(Entry(i.toFloat(), (data[i].humidity).toFloat()))
        }

        val set = LineDataSet(entries, "Humidity data")
        set.color = Color.BLUE
        set.lineWidth = 2.5f
        set.setCircleColor(Color.BLUE)
        set.circleRadius = 2f
        set.fillColor = Color.BLUE
        set.mode = LineDataSet.Mode.CUBIC_BEZIER
        set.setDrawValues(true)
        set.valueTextSize = 7f
        set.valueTextColor = Color.BLUE

        set.axisDependency = YAxis.AxisDependency.LEFT

        set.axisDependency = YAxis.AxisDependency.LEFT
        lineData.addDataSet(set)

        return set
    }

    private fun generateSoilChart(data: List<Data>, binding: FragmentChartBinding) {
        val soilChart = binding.soilChart
        soilChart.description.isEnabled = false
        soilChart.setDrawGridBackground(false)
        soilChart.setDrawBarShadow(false)
        soilChart.isHighlightFullBarEnabled = false

        val rightAxis: YAxis = soilChart.axisRight
        rightAxis.setDrawGridLines(false)
        rightAxis.axisMinimum = -100f

        val leftAxis: YAxis = soilChart.axisLeft
        leftAxis.setDrawGridLines(false)
        leftAxis.axisMinimum = -100f

        val xAxis: XAxis = soilChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.mAxisMinimum = 0f
        xAxis.granularity = 1f

        val combinedData = CombinedData()
        val lineData = LineData()
        lineData.addDataSet(dataSoilChart(data) as ILineDataSet)
        combinedData.setData(lineData)
        xAxis.axisMaximum = combinedData.xMax + 0.25f
        soilChart.data = combinedData
        soilChart.invalidate()
    }

    private fun dataSoilChart(data: List<Data>): DataSet<Entry> {
        val lineData = LineData()

        val entries = ArrayList<Entry>()
        for (i in data.indices) {
            entries.add(Entry(i.toFloat(), (data[i].soilMoisture).toFloat()))
        }

        val set = LineDataSet(entries, "Soil moisture data")
        set.color = Color.BLUE
        set.lineWidth = 2.5f
        set.setCircleColor(Color.BLUE)
        set.circleRadius = 2f
        set.fillColor = Color.BLUE
        set.mode = LineDataSet.Mode.CUBIC_BEZIER
        set.setDrawValues(true)
        set.valueTextSize = 7f
        set.valueTextColor = Color.BLUE

        set.axisDependency = YAxis.AxisDependency.LEFT

        set.axisDependency = YAxis.AxisDependency.LEFT
        lineData.addDataSet(set)

        return set
    }

    private fun isNumeric(number: String): Boolean {
        try {
            val d: Float = number.toFloat()
        } catch (nfe: NumberFormatException) {
            return false
        }
        return true
    }
}
package com.example.avicenna_diagnostics.ui.glucometer

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.avicenna_diagnostics.databinding.FragmentGlucometerBinding
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay



class GlucometerFragment : Fragment() {

    private var _binding: FragmentGlucometerBinding? = null
    private val binding get() = _binding!!
    private val glucometerViewModel: GlucometerViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGlucometerBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val chart = binding.chart // Make sure binding.chart refers to your LineChart instance
        chart.setNoDataText("");
        resetChart();
        configureXAxis(binding.chart)


        val promptTextView: TextView = binding.promptTextView
        glucometerViewModel.text.observe(viewLifecycleOwner) {
            promptTextView.text = it
        }

        // Update the prompt text if using text_home or ensure your promptTextView has the correct message
        // binding.textHome.text = "Collect the blood sample and press the button once done."

        binding.fab.setOnClickListener { view ->
            // Optional: Display a Snackbar as feedback
            Snackbar.make(view, "Reading Glucose Level in Progress", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

            // Start drawing the graph and hide the FAB and the prompt
            binding.fab.visibility = View.GONE
            binding.promptTextView.visibility = View.GONE // Use textHome instead if you're repurposing it
            glucometerViewModel.setFlag(true)



            lifecycleScope.launch {
                glucometerViewModel.glucoseLevels.collect { dataPoint ->
                    dataPoint?.let {
                        addEntryToChart(it)
                    }
                    delay(100) // Wait for 0.1 second before adding the next point
                    glucometerViewModel.getNextDataPoint()
                }
            }
            //glucometerViewModel.getNextDataPoint() // Start the data feed

        }

        return root
    }
    private fun addEntryToChart(dataPoint: Pair<Float, Float>) {
        val chart = binding.chart
        var lineData = chart.data
        Log.d("ChartDebug", "Adding Entry: $dataPoint")


        if (lineData == null) {
            lineData = LineData()
            chart.data = lineData
        }

        var set = lineData.getDataSetByIndex(0)

        if (set == null) {
            set = LineDataSet(null, "Glucose Levels")
            lineData.addDataSet(set)
        }

        lineData.addEntry(Entry(dataPoint.first, dataPoint.second), 0)
        lineData.notifyDataChanged()
        chart.notifyDataSetChanged()
        chart.setVisibleXRangeMaximum(10f) // Adjust this value as needed
        chart.moveViewToX(dataPoint.first)
    }
    private fun resetChart() {
        with(binding.chart) {
            clear() // Clear any old data or configurations
            data = null // Explicitly nullify the chart's data
            invalidate() // Refresh the chart
        }
        // Here, reapply any essential chart configurations
    }

    private fun configureXAxis(chart: LineChart) {
        with(chart.xAxis) {
            position = XAxis.XAxisPosition.BOTTOM
            setDrawGridLines(true) // Depending on your preference
            granularity = 1f // Show every integer
            axisMinimum = 1f // Start from 1
            axisMaximum = 10f // End at 10
            valueFormatter = DefaultAxisValueFormatter(0)
            textColor = Color.WHITE
        }
        // YAxis Configuration for both sides
        chart.axisLeft.textColor = Color.WHITE  // Left Y-axis
        chart.axisRight.textColor = Color.WHITE // Right Y-axis

        // Legend Configuration
        chart.legend.textColor = Color.WHITE

        // Call this to ensure the chart is updated with the new configuration
        chart.invalidate()
    }


    override fun onDestroyView() {
        Log.d("DataPoint", "On Destroy called") // Add this line to log the current index
        super.onDestroyView()
        binding.chart.data?.clearValues()
        binding.chart.notifyDataSetChanged()
        binding.chart.invalidate()
        binding.chart.clear() // Clear the chart data
        glucometerViewModel.getNextDataPoint() // Start the data feed




        _binding = null
    }
}




//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = FragmentGlucometerBinding.inflate(inflater, container, false)
//        val root: View = binding.root
//
//
//
//        binding.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Reading Glucose Level in Progress", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
//
//        binding.fab.setOnClickListener {
//            startDrawingGraph()
//            it.visibility = View.GONE // Make the FAB disappear
//        }
//
//        return root
//    }

//    private fun startDrawingGraph() {
//        lifecycleScope.launch {
//            glucometerViewModel.glucoseLevels.collect { dataPoint ->
//                dataPoint?.let {
//                    addEntryToChart(it)
//                    glucometerViewModel.getNextDataPoint()
//                }
//            }
//        }
//    }




/**
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.avicenna_diagnostics.databinding.FragmentGlucometerBinding
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GlucometerFragment : Fragment() {

    private var _binding: FragmentGlucometerBinding? = null
    private val binding get() = _binding!!
    private val glucometerViewModel: GlucometerViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentGlucometerBinding.inflate(inflater, container, false)
        val root: View = binding.root

        lifecycleScope.launch {
            glucometerViewModel.glucoseLevels.collect { dataPoint ->
                dataPoint?.let {
                    addEntryToChart(it)
                }
                delay(500) // Wait for half a second before adding the next point
                glucometerViewModel.getNextDataPoint()
            }
        }

        glucometerViewModel.getNextDataPoint() // Start the data feed

        return root
    }

    private fun addEntryToChart(dataPoint: Pair<Float, Float>) {
        val chart = binding.chart
        var lineData = chart.data

        if (lineData == null) {
            lineData = LineData()
            chart.data = lineData
        }

        var set = lineData.getDataSetByIndex(0)

        if (set == null) {
            set = LineDataSet(null, "Glucose Levels")
            lineData.addDataSet(set)
        }

        lineData.addEntry(Entry(dataPoint.first, dataPoint.second), 0)
        lineData.notifyDataChanged()
        chart.notifyDataSetChanged()
        chart.setVisibleXRangeMaximum(10f) // Adjust this value as needed
        chart.moveViewToX(dataPoint.first)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
**/
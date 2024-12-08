package com.example.GamblingLogger

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.coroutines.runBlocking

private const val TAG = "StatsFragment"
class StatsFragment : Fragment() {
    private lateinit var lineChart: LineChart
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_stats, container, false)


        lifecycleScope.launch(Dispatchers.IO) {
            var  totalProfit = (activity?.application as GamblingLoggerApp).db.wagerDao().totalProfitOrLoss()
            view.findViewById<TextView?>(R.id.total_profit_or_loss_amount_value).text = "$" + totalProfit

            var wins = (activity?.application as GamblingLoggerApp).db.wagerDao().wins()
            var losses = (activity?.application as GamblingLoggerApp).db.wagerDao().losses()
            view.findViewById<TextView?>(R.id.win_loss_ratio_value).text = wins.toString() + " - " + losses.toString()

            lineChart = view.findViewById(R.id.money_line_chart)

            val entries = mutableListOf<Entry>()

            var entriesCounter = 0
            val wagerFlow = (activity?.application as GamblingLoggerApp).db.wagerDao().getAll()

            wagerFlow.collect {wagers ->
                for (wager in wagers) {
                    entries.add(Entry(entriesCounter.toFloat(), wager.profitOrLoss.toFloat()))
                    entriesCounter += 1

                    val lineDataSet = LineDataSet(entries, "Total Money")
                    lineDataSet.color = Color.BLUE
                    lineDataSet.valueTextColor = Color.BLACK



                    val lineData = LineData(lineDataSet)
                    val xAxis = lineChart.xAxis
                    xAxis.position = XAxis.XAxisPosition.BOTTOM
                    lineChart.data = lineData

                    lineChart.invalidate()

                }
            }
        }

        return view
    }
    companion object {

        fun newInstance(): StatsFragment{
            return StatsFragment()
        }
    }
}
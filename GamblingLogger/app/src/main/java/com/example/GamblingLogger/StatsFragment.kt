package com.example.GamblingLogger

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "StatsFragment"
class StatsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_stats, container, false)
        lifecycleScope.launch(Dispatchers.IO) {
            var  totalProfit = (activity?.application as GamblingLoggerApp).db.wagerDao().totalProfitOrLoss()
            view.findViewById<TextView?>(R.id.total_profit_or_loss_amount_value).text = "$" + totalProfit
        }

        return view
    }
    companion object {

        fun newInstance(): StatsFragment{
            return StatsFragment()
        }
    }
}
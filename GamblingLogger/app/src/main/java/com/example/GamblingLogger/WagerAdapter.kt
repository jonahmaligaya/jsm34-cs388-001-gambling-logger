package com.example.GamblingLogger

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class WagerAdapter(
    private val context: Context,
    private val wager: MutableList<Wager>) : RecyclerView.Adapter<WagerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.wager_fragment, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val wagerEntity = wager[position]
        holder.bind(wagerEntity)
    }

    override fun getItemCount() : Int{
        return wager.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val wagerProfitOrLoss = itemView.findViewById<TextView>(R.id.wager_profit_or_loss)
        private val wagerOdds = itemView.findViewById<TextView>(R.id.wager_odds)
        private val wagerTitle = itemView.findViewById<TextView>(R.id.wager_title)

        fun bind(wagerItem: Wager) {
            wagerProfitOrLoss.text = wagerItem.profitOrLoss
            wagerOdds.text = wagerItem.wagerOdds
            wagerTitle.text = wagerItem.wagerTitle
        }
    }
}
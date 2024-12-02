package com.example.GamblingLogger

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch


class WagerAdapter(
    private val context: Context,
    private val wager: MutableList<Wager>,
    private val deleteWager: (String) -> Unit) : RecyclerView.Adapter<WagerAdapter.ViewHolder>() {

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
        private val buttonDeleteWager: Button = itemView.findViewById(R.id.delete_wager_button)
        private val buttonShareWager: Button = itemView.findViewById(R.id.share_wager_button)

        fun bind(wagerItem: Wager) {
            wagerProfitOrLoss.text = wagerItem.profitOrLoss
            wagerOdds.text = wagerItem.wagerOdds
            wagerTitle.text = wagerItem.wagerTitle
            buttonDeleteWager.setOnClickListener() {
                deleteWager(wagerItem.wagerTitle)
            }
            buttonShareWager.setOnClickListener() {
                val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val shared_text = "Hi friend! I placed a bet on " + wagerItem.wagerTitle + " with the odds of " + wagerItem.wagerOdds + " and made " + wagerItem.profitOrLoss + "!"
                val clip = ClipData.newPlainText("label", shared_text)
                clipboard.setPrimaryClip(clip)
            }
        }
    }
}
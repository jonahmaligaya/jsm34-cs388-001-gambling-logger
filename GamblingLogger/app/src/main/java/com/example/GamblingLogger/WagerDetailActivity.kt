package com.example.GamblingLogger

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class WagerDetailActivity : AppCompatActivity() {
    private lateinit var wagerProfitOrLossInput: EditText
    private lateinit var wagerOddsInput: EditText
    private lateinit var wagerTitleInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_wager_detail_activity)

        // TODO: Find the views for the screen
        wagerProfitOrLossInput = findViewById(R.id.enter_profit_or_loss_amount)
        wagerOddsInput = findViewById(R.id.enter_odds)
        wagerTitleInput = findViewById(R.id.enter_title)


        findViewById<Button>(R.id.add_wager_button_detail).setOnClickListener{
            val newWagerEntity = Wager(
                wagerProfitOrLossInput.text.toString(),
                wagerOddsInput.text.toString(),
                wagerTitleInput.text.toString()
            )

            lifecycleScope.launch(IO) {
                (application as GamblingLoggerApp).db.wagerDao().insert(newWagerEntity)
            }


            wagerProfitOrLossInput.getText().clear()
            wagerOddsInput.getText().clear()
            wagerTitleInput.getText().clear()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
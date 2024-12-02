package com.example.GamblingLogger

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wager_table")
data class Wager (
    @ColumnInfo val profitOrLoss : String,
    @ColumnInfo val wagerOdds : String,
    @ColumnInfo val wagerTitle : String,
    @PrimaryKey(autoGenerate = true) val id : Long =0,
)

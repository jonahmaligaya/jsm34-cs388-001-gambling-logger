package com.example.GamblingLogger

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
@Dao
interface WagerDao {
    @Query("SELECT * FROM wager_table")
    fun getAll(): Flow<List<Wager>>

    @Insert
    fun insertAll(wagers: List<Wager>)

    @Insert
    fun insert(wager: Wager)

    @Query("SELECT sum(profitOrLoss) FROM wager_table")
    fun totalProfitOrLoss() : String

    @Query("SELECT COUNT(*) FROM wager_table WHERE profitOrLoss > 0")
    fun wins() : Int

    @Query("SELECT COUNT(*) FROM wager_table WHERE profitOrLoss < 0")
    fun losses() : Int

    @Query("DELETE FROM wager_table")
    fun deleteAll()
}
package com.example.GamblingLogger

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Wager::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun wagerDao(): WagerDao

    companion object {

        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDataBase::class.java, "water_database"
            ).build()
    }
}

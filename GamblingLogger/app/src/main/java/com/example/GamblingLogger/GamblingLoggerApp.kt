package com.example.GamblingLogger

import android.app.Application

class GamblingLoggerApp : Application() {
    val db by lazy { AppDataBase.getInstance(this) }
}
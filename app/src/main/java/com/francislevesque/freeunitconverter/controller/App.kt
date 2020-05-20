package com.francislevesque.freeunitconverter.controller

import android.app.Application
import com.francislevesque.freeunitconverter.utilites.SharedPrefs

class App : Application() {

    companion object {
        lateinit var prefs: SharedPrefs
    }

    override fun onCreate() {
        prefs = SharedPrefs(applicationContext)
        super.onCreate()
    }
}
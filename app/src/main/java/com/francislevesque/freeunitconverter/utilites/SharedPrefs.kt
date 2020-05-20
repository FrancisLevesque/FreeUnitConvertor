package com.francislevesque.freeunitconverter.utilites

import android.content.Context
import android.content.SharedPreferences
import com.francislevesque.freeunitconverter.model.Units

class SharedPrefs(context: Context) {
    private val PREFS_FILENAME = "com.francislevesque.freeunitconverter.prefs"
    private val LAST_USED_CATEGORY = "lastUsedCategory"
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)

    var lastUsedCategory: String
        get() = prefs.getString(LAST_USED_CATEGORY, Units.defaultCategory()).toString()
        set(value) = prefs.edit().putString(LAST_USED_CATEGORY, value).apply()
}
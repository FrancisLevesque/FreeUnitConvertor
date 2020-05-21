package com.francislevesque.freeunitconverter.utilites

import android.content.Context
import android.content.SharedPreferences
import com.francislevesque.freeunitconverter.model.Units

class SharedPrefs(context: Context) {
    private val PREFS_FILENAME = "com.francislevesque.freeunitconverter.prefs"
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)
    private val LAST_USED_CATEGORY = "lastUsedCategory"
    private val LAST_USED_FROM_UNIT = "lastUsedFromUnit"
    private val LAST_USED_TO_UNIT = "lastUsedToUnit"

    var lastUsedCategory: String
        get() {
            val a = prefs.getString(LAST_USED_CATEGORY, Units.defaultCategory()).toString()
            println("------- GETTING CATEGORY: $a")
            return a
        }
        set(value) {
            println("------- SETTING CATEGORY: $value")
            prefs.edit().putString(LAST_USED_CATEGORY, value).apply()
        }

    var fromUnit: String
        get() {
            val b = prefs.getString(LAST_USED_FROM_UNIT, Units.getCategoryFromName(lastUsedCategory).getDefaultUnit().name).toString()
            println("------- GETTING FROM UNIT: $b")
            return b
        }
        set(value) {
            println("------- SETTING FROM UNIT: $value")
            return prefs.edit().putString(LAST_USED_FROM_UNIT, value).apply()
        }

    var toUnit: String
        get() {
            val a = prefs.getString(LAST_USED_TO_UNIT, Units.getCategoryFromName(lastUsedCategory).getDefaultUnit().name).toString()
            println("------- GETTING TO UNIT: $a")
            return a
        }
        set(value) {
            println("------- SETTING TO UNIT: $value")
            return prefs.edit().putString(LAST_USED_TO_UNIT, value).apply()
        }
}
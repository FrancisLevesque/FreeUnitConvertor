package com.francislevesque.freeunitconverter.model

import android.util.Log

object Units {
    val categories = listOf(
//        "amount of a substance",
        "distance",
        "electric current",
        "luminous intensity",
        "mass",
        "temperature",
        "time"
    )

    private val distanceUnits = arrayListOf<Unit>(
        Unit("centimeter", "cm", "distance", 0.01f),
        Unit("kilometer", "km", "distance", 1000.0f),
        Unit("meter", "m", "distance", 1.0f),
        Unit("foot", "ft", "distance", 0.3048f)
    )

//    private val amountUnits = arrayListOf<Unit>(
//        Unit("mole", "mol", "amount of a substance")
//    )
//
//    private val electricCurrentUnits = arrayListOf<Unit>(
//        Unit("ampere", "A", "electric current")
//    )
//
//    private val luminousIntensityUnits = arrayListOf<Unit>(
//        Unit("candela", "cd", "luminous intensity")
//    )
//
//    private val massUnits = arrayListOf<Unit>(
//        Unit("kilogram", "kg", "mass")
//    )
//
//    private val temperatureUnits = arrayListOf<Unit>(
//        Unit("kelvin", "K", "temperature")
//    )
//
//    private val timeUnits = arrayListOf<Unit>(
//        Unit("second", "s", "time")
//    )

    fun unitsFor(category: String): List<Unit> {
        return when(category) {
            "distance" -> distanceUnits
//            "electric current" -> electricCurrentUnits
//            "luminous intensity" -> luminousIntensityUnits
//            "mass" -> massUnits
//            "temperature" -> temperatureUnits
//            "time" -> timeUnits
//            "amount of a substance" -> amountUnits
            else -> {
                Log.e("ERROR:", "Unknown category $category passed in!")
                listOf()
            }
        }
    }
}
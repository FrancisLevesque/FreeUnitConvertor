package com.francislevesque.freeunitconverter.model

import android.util.Log

object Units {
    val categories = listOf(
        "distance",
        "cooking",
        "electric current",
        "luminous intensity",
        "mass",
        "temperature",
        "time"
    )

    private val distanceUnits = arrayListOf<Unit>(
        // TODO: Introduce way to flag base unit and set as default (probably with classes)
        Unit("nanometer", "nm", "distance", 0.000000001f),
        Unit("micrometer", "μm", "distance", 0.000001f),
        Unit("millimeter", "mm", "distance", 0.001f),
        Unit("centimeter", "cm", "distance", 0.01f),
        Unit("decimeter", "dm", "distance", 0.1f),
        Unit("meter", "m", "distance", 1.0f),
        Unit("kilometer", "km", "distance", 1000.0f),
        Unit("inch", "in", "distance", 0.0254f),
        Unit("foot", "ft", "distance", 0.3048f),
        Unit("yard", "yd", "distance", 0.91f),
        Unit("mile", "mi", "distance", 1610.0f),
        Unit("fathom", "mi", "distance", 1.8288f),
        Unit("nautical mile", "nmi", "distance", 1852.0f)
    )

    private val electricCurrentUnits = arrayListOf<Unit>(
        Unit("ampere", "A", "electric current", 1.0f)
    )

    private val luminousIntensityUnits = arrayListOf<Unit>(
        Unit("candela", "cd", "luminous intensity", 1.0f)
    )

    private val massUnits = arrayListOf<Unit>(
        Unit("kilogram", "kg", "mass", 1.0f)
    )

    private val temperatureUnits = arrayListOf<Unit>(
        Unit("kelvin", "K", "temperature", 1.0f)
    )

    private val timeUnits = arrayListOf<Unit>(
        Unit("nanosecond", "ns", "distance", 0.000000001f),
        Unit("microsecond", "μs", "distance", 0.000001f),
        Unit("millisecond", "ms", "distance", 0.001f),
        Unit("second", "s", "time", 1.0f),
        Unit("minute", "min", "time", 60.0f),
        Unit("hour", "h", "time", 3600.0f),
        Unit("day", "d", "time", 86400.0f),
        Unit("week", "w", "time", 604800.0f)
    )

    fun unitsFor(category: String): List<Unit> {
        return when(category) {
            "distance" -> distanceUnits
            "electric current" -> electricCurrentUnits
            "luminous intensity" -> luminousIntensityUnits
            "mass" -> massUnits
            "temperature" -> temperatureUnits
            "time" -> timeUnits
            else -> {
                Log.e("ERROR:", "Unknown category $category passed in!")
                listOf()
            }
        }
    }
}
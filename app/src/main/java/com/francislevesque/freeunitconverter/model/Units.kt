package com.francislevesque.freeunitconverter.model

import android.util.Log
import java.math.BigDecimal

object Units {
    val categories = listOf(
        "distance",
//        "cooking", Create this categories based on tags from other categories
//        "electric current",
//        "luminous intensity",
        "mass",
//        "temperature",
        "time"
//      "volume" // https://en.wikibooks.org/wiki/Cookbook:Units_of_measurement
    )

    private val distanceUnits = arrayListOf<Unit>(
        // TODO: Introduce way to flag base unit and set as default (probably with classes)
        Unit("nanometer", "nm", "distance", BigDecimal.valueOf(0.000000001)),
        Unit("micrometer", "μm", "distance", BigDecimal.valueOf(0.000001)),
        Unit("millimeter", "mm", "distance", BigDecimal.valueOf(0.001)),
        Unit("centimeter", "cm", "distance", BigDecimal.valueOf(0.01)),
        Unit("decimeter", "dm", "distance", BigDecimal.valueOf(0.1)),
        Unit("meter", "m", "distance", BigDecimal.valueOf(1.0)),
        Unit("kilometer", "km", "distance", BigDecimal.valueOf(1000.0)),
        Unit("inch", "in", "distance", BigDecimal.valueOf(0.0254)),
        Unit("foot", "ft", "distance", BigDecimal.valueOf(0.3048)),
        Unit("yard", "yd", "distance", BigDecimal.valueOf(0.91)),
        Unit("mile", "mi", "distance", BigDecimal.valueOf(1610.0)),
        Unit("fathom", "mi", "distance", BigDecimal.valueOf(1.8288)),
        Unit("nautical mile", "nmi", "distance", BigDecimal.valueOf(1852.0))
    )

//    private val electricCurrentUnits = arrayListOf<Unit>(
//        Unit("ampere", "A", "electric current", BigDecimal.valueOf(1.0))
//    )
//
//    private val luminousIntensityUnits = arrayListOf<Unit>(
//        Unit("candela", "cd", "luminous intensity", BigDecimal.valueOf(1.0))
//    )

    private val massUnits = arrayListOf<Unit>(
        Unit("milligram", "mg", "mass", BigDecimal.valueOf(0.001)),
        Unit("gram", "g", "mass", BigDecimal.valueOf(1.0)),
        Unit("kilogram", "kg", "mass", BigDecimal.valueOf(1000.0)),
        Unit("pound", "lb", "mass", BigDecimal.valueOf(453.59237)),
        Unit("ounce", "oz", "mass", BigDecimal.valueOf(28.34952))
    )

    // Need to build out the factor before this can work: https://en.wikipedia.org/wiki/Conversion_of_units_of_temperature
//    private val temperatureUnits = arrayListOf<Unit>(
//        Unit("kelvin", "K", "temperature", BigDecimal.valueOf(1.0)),
//        Unit("celsius", "°C", "temperature", BigDecimal.valueOf(1.0)),
//        Unit("fahrenheit", "°F", "temperature", BigDecimal.valueOf(1.0))
//    )

    private val timeUnits = arrayListOf<Unit>(
        Unit("nanosecond", "ns", "distance", BigDecimal.valueOf(0.000000001)),
        Unit("microsecond", "μs", "distance", BigDecimal.valueOf(0.000001)),
        Unit("millisecond", "ms", "distance", BigDecimal.valueOf(0.001)),
        Unit("second", "s", "time", BigDecimal.valueOf(1.0)),
        Unit("minute", "min", "time", BigDecimal.valueOf(60.0)),
        Unit("hour", "h", "time", BigDecimal.valueOf(3600.0)),
        Unit("day", "d", "time", BigDecimal.valueOf(86400.0)),
        Unit("week", "w", "time", BigDecimal.valueOf(604800.0))
    )

    fun unitsFor(category: String): List<Unit> {
        return when(category) {
            "distance" -> distanceUnits
//            "electric current" -> electricCurrentUnits
//            "luminous intensity" -> luminousIntensityUnits
            "mass" -> massUnits
//            "temperature" -> temperatureUnits
            "time" -> timeUnits
            else -> {
                Log.e("ERROR:", "Unknown category $category passed in!")
                listOf()
            }
        }
    }
}
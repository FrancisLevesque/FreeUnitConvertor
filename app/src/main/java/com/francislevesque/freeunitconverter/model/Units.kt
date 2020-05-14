package com.francislevesque.freeunitconverter.model

import android.util.Log
import java.math.BigDecimal

object Units {
    private val allUnits = arrayListOf<Unit>(
        // DISTANCE
        Unit("nanometer", "nm", arrayListOf("distance"), BigDecimal.valueOf(0.000000001)),
        Unit("micrometer", "μm", arrayListOf("distance"), BigDecimal.valueOf(0.000001)),
        Unit("millimeter", "mm", arrayListOf("distance"), BigDecimal.valueOf(0.001)),
        Unit("centimeter", "cm", arrayListOf("distance"), BigDecimal.valueOf(0.01)),
        Unit("decimeter", "dm", arrayListOf("distance"), BigDecimal.valueOf(0.1)),
        Unit("meter", "m", arrayListOf("distance"), BigDecimal.valueOf(1.0)),
        Unit("kilometer", "km", arrayListOf("distance"), BigDecimal.valueOf(1000.0)),
        Unit("inch", "in", arrayListOf("distance"), BigDecimal.valueOf(0.0254)),
        Unit("foot", "ft", arrayListOf("distance"), BigDecimal.valueOf(0.3048)),
        Unit("yard", "yd", arrayListOf("distance"), BigDecimal.valueOf(0.9144)),
        Unit("mile", "mi", arrayListOf("distance"), BigDecimal.valueOf(1609.344)),
        Unit("fathom", "mi", arrayListOf("distance"), BigDecimal.valueOf(1.8288)),
        Unit("nautical mile", "nmi", arrayListOf("distance"), BigDecimal.valueOf(1852.0)),
        // MASS
        Unit("milligram", "mg", arrayListOf("mass", "weight"), BigDecimal.valueOf(0.001)),
        Unit("gram", "g", arrayListOf("mass", "weight", "cooking"), BigDecimal.valueOf(1.0)),
        Unit("kilogram", "kg", arrayListOf("mass", "weight"), BigDecimal.valueOf(1000.0)),
        Unit("ounce", "oz", arrayListOf("mass", "cooking"), BigDecimal.valueOf(28.34952)),
        Unit("pound", "lb", arrayListOf("mass"), BigDecimal.valueOf(453.59237)),
        // TIME
        Unit("nanosecond", "ns", arrayListOf("time"), BigDecimal.valueOf(0.000000001)),
        Unit("microsecond", "μs", arrayListOf("time"), BigDecimal.valueOf(0.000001)),
        Unit("millisecond", "ms", arrayListOf("time"), BigDecimal.valueOf(0.001)),
        Unit("second", "s", arrayListOf("time"), BigDecimal.valueOf(1.0)),
        Unit("minute", "min", arrayListOf("time"), BigDecimal.valueOf(60.0)),
        Unit("hour", "h", arrayListOf("time"), BigDecimal.valueOf(3600.0)),
        Unit("day", "d", arrayListOf("time"), BigDecimal.valueOf(86400.0)),
        Unit("week", "w", arrayListOf("time"), BigDecimal.valueOf(604800.0))
    )

    val distance = Category(allUnits, "distance", "meter")
    val mass = Category(allUnits, "mass", "gram")
    val time = Category(allUnits, "time", "second")

    val categories = listOf(
        distance.tag,
//        "cooking", Create this categories based on tags from other categories
//        "electric current",
//        "luminous intensity",
        mass.tag,
//        "temperature",
        time.tag
//      "volume" // https://en.wikibooks.org/wiki/Cookbook:Units_of_measurement
    )

//    private val electricCurrentUnits = arrayListOf<Unit>(
//        Unit("ampere", "A", "electric current", BigDecimal.valueOf(1.0))
//    )
//
//    private val luminousIntensityUnits = arrayListOf<Unit>(
//        Unit("candela", "cd", "luminous intensity", BigDecimal.valueOf(1.0))
//    )

    // Need to build out the factor before this can work: https://en.wikipedia.org/wiki/Conversion_of_units_of_temperature
//    private val temperatureUnits = arrayListOf<Unit>(
//        Unit("kelvin", "K", "temperature", BigDecimal.valueOf(1.0)),
//        Unit("celsius", "°C", "temperature", BigDecimal.valueOf(1.0)),
//        Unit("fahrenheit", "°F", "temperature", BigDecimal.valueOf(1.0))
//    )

    fun defaultCategory(): String {
        return categories.first()
    }

    fun setCategory(category: String): Category {
        return when(category) {
            "distance" -> distance
//            "electric current" -> electricCurrentUnits
//            "luminous intensity" -> luminousIntensityUnits
            "mass" -> mass
//            "temperature" -> temperatureUnits
            "time" -> time
            else -> {
                Log.e("ERROR:", "Unknown category $category passed in!")
                Category(arrayListOf<Unit>(), "n/a", "n/a")
            }
        }
    }
}
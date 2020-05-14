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

    private val nanometer = Unit("nanometer", "nm", "distance", BigDecimal.valueOf(0.000000001))
    private val micrometer = Unit("micrometer", "μm", "distance", BigDecimal.valueOf(0.000001))
    private val millimeter = Unit("millimeter", "mm", "distance", BigDecimal.valueOf(0.001))
    private val centimeter = Unit("centimeter", "cm", "distance", BigDecimal.valueOf(0.01))
    private val decimeter = Unit("decimeter", "dm", "distance", BigDecimal.valueOf(0.1))
    private val meter = Unit("meter", "m", "distance", BigDecimal.valueOf(1.0))
    private val kilometer = Unit("kilometer", "km", "distance", BigDecimal.valueOf(1000.0))
    private val inch = Unit("inch", "in", "distance", BigDecimal.valueOf(0.0254))
    private val foot = Unit("foot", "ft", "distance", BigDecimal.valueOf(0.3048))
    private val yard = Unit("yard", "yd", "distance", BigDecimal.valueOf(0.91))
    private val mile = Unit("mile", "mi", "distance", BigDecimal.valueOf(1610.0))
    private val fathom = Unit("fathom", "mi", "distance", BigDecimal.valueOf(1.8288))
    private val nautical = Unit("nautical mile", "nmi", "distance", BigDecimal.valueOf(1852.0))

    private val distanceUnits = arrayListOf<Unit>(
        nanometer, micrometer, millimeter, centimeter, decimeter, meter, kilometer, inch, foot, yard, mile, fathom, nautical
    )
    private val distance = Category(distanceUnits, meter)

//    private val electricCurrentUnits = arrayListOf<Unit>(
//        Unit("ampere", "A", "electric current", BigDecimal.valueOf(1.0))
//    )
//
//    private val luminousIntensityUnits = arrayListOf<Unit>(
//        Unit("candela", "cd", "luminous intensity", BigDecimal.valueOf(1.0))
//    )

    private val milligram = Unit("milligram", "mg", "mass", BigDecimal.valueOf(0.001))
    private val gram = Unit("gram", "g", "mass", BigDecimal.valueOf(1.0))
    private val kilogram = Unit("kilogram", "kg", "mass", BigDecimal.valueOf(1000.0))
    private val pound = Unit("pound", "lb", "mass", BigDecimal.valueOf(453.59237))
    private val ounce = Unit("ounce", "oz", "mass", BigDecimal.valueOf(28.34952))
    private val massUnits = arrayListOf<Unit>(
        milligram, gram, kilogram, pound, ounce
    )
    private val mass = Category(massUnits, gram)

    // Need to build out the factor before this can work: https://en.wikipedia.org/wiki/Conversion_of_units_of_temperature
//    private val temperatureUnits = arrayListOf<Unit>(
//        Unit("kelvin", "K", "temperature", BigDecimal.valueOf(1.0)),
//        Unit("celsius", "°C", "temperature", BigDecimal.valueOf(1.0)),
//        Unit("fahrenheit", "°F", "temperature", BigDecimal.valueOf(1.0))
//    )

    private val nanosecond = Unit("nanosecond", "ns", "distance", BigDecimal.valueOf(0.000000001))
    private val microsecond = Unit("microsecond", "μs", "distance", BigDecimal.valueOf(0.000001))
    private val millisecond = Unit("millisecond", "ms", "distance", BigDecimal.valueOf(0.001))
    private val second = Unit("second", "s", "time", BigDecimal.valueOf(1.0))
    private val minute = Unit("minute", "min", "time", BigDecimal.valueOf(60.0))
    private val hour = Unit("hour", "h", "time", BigDecimal.valueOf(3600.0))
    private val day = Unit("day", "d", "time", BigDecimal.valueOf(86400.0))
    private val week = Unit("week", "w", "time", BigDecimal.valueOf(604800.0))
    private val timeUnits = arrayListOf<Unit>(
        nanosecond, microsecond, millisecond, second, minute, hour, day, week
    )
    private val time = Category(timeUnits, second)

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
                Category(arrayListOf<Unit>(), Unit("n/a", "n/a", "n/a", BigDecimal.valueOf(0.0)))
            }
        }
    }
}
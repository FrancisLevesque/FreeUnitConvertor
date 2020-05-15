package com.francislevesque.freeunitconverter.model

import android.util.Log
import java.math.BigDecimal

object Units {
    private val baseConverter = FactorConverter(BigDecimal.valueOf(1.0))

    private val nanoConverter = FactorConverter(BigDecimal.valueOf(0.000000001))
    private val microConverter = FactorConverter(BigDecimal.valueOf(0.000001))
    private val milliConverter = FactorConverter(BigDecimal.valueOf(0.001))
    private val centiConverter = FactorConverter(BigDecimal.valueOf(0.01))
    private val deciConverter = FactorConverter(BigDecimal.valueOf(0.1))
    private val kiloConverter = FactorConverter(BigDecimal.valueOf(1000.0))
    private val megaConverter = FactorConverter(BigDecimal.valueOf(1000000.0))
    private val gigaConverter = FactorConverter(BigDecimal.valueOf(1000000000.0))

    private val inchConverter = FactorConverter(BigDecimal.valueOf(0.0254))
    private val footConverter = FactorConverter(BigDecimal.valueOf(0.3048))
    private val yardConverter = FactorConverter(BigDecimal.valueOf(0.9144))
    private val mileConverter = FactorConverter(BigDecimal.valueOf(1609.344))
    private val fathomConverter = FactorConverter(BigDecimal.valueOf(1.8288))
    private val nauticalMileConverter = FactorConverter(BigDecimal.valueOf(1852.0))

    private val gramConverter = FactorConverter(BigDecimal.valueOf(1.0))
    private val ounceConverter = FactorConverter(BigDecimal.valueOf(28.34952))
    private val poundConverter = FactorConverter(BigDecimal.valueOf(453.59237))

    private val minuteConverter = FactorConverter(BigDecimal.valueOf(60.0))
    private val hourConverter = FactorConverter(BigDecimal.valueOf(3600.0))
    private val dayConverter = FactorConverter(BigDecimal.valueOf(86400.0))
    private val weekConverter = FactorConverter(BigDecimal.valueOf(604800.0))

    private val kelvinConverter = StepConverter(BigDecimal.valueOf(273.15))

    private val allUnits = arrayListOf<Unit>(
        // DISTANCE
        Unit("nanometer", "nm", arrayListOf("distance"), nanoConverter),
        Unit("micrometer", "μm", arrayListOf("distance"), microConverter),
        Unit("millimeter", "mm", arrayListOf("distance"), milliConverter),
        Unit("centimeter", "cm", arrayListOf("distance"), centiConverter),
        Unit("decimeter", "dm", arrayListOf("distance"), deciConverter),
        Unit("meter", "m", arrayListOf("distance"), baseConverter),
        Unit("kilometer", "km", arrayListOf("distance"), kiloConverter),
        Unit("inch", "in", arrayListOf("distance"), inchConverter),
        Unit("foot", "ft", arrayListOf("distance"), footConverter),
        Unit("yard", "yd", arrayListOf("distance"), yardConverter),
        Unit("mile", "mi", arrayListOf("distance"), mileConverter),
        Unit("fathom", "mi", arrayListOf("distance"), fathomConverter),
        Unit("nautical mile", "nmi", arrayListOf("distance"), nauticalMileConverter),
        // MASS
        Unit("milligram", "mg", arrayListOf("mass", "weight"), milliConverter),
        Unit("gram", "g", arrayListOf("mass", "weight", "cooking"), gramConverter),
        Unit("kilogram", "kg", arrayListOf("mass", "weight"), kiloConverter),
        Unit("ounce", "oz", arrayListOf("mass", "cooking"), ounceConverter),
        Unit("pound", "lb", arrayListOf("mass"), poundConverter),
        // TIME
        Unit("nanosecond", "ns", arrayListOf("time"), nanoConverter),
        Unit("microsecond", "μs", arrayListOf("time"), microConverter),
        Unit("millisecond", "ms", arrayListOf("time"), milliConverter),
        Unit("second", "s", arrayListOf("time"), baseConverter),
        Unit("minute", "min", arrayListOf("time"), minuteConverter),
        Unit("hour", "h", arrayListOf("time"), hourConverter),
        Unit("day", "d", arrayListOf("time"), dayConverter),
        Unit("week", "w", arrayListOf("time"), weekConverter),
        // TEMPERATURE
        Unit("kelvin", "K", arrayListOf("temperature"), kelvinConverter),
        Unit("celsius", "°C", arrayListOf("temperature"), baseConverter),
        Unit("fahrenheit", "°F", arrayListOf("temperature"), FahrenheitConverter()),
        // EMPTY (for the error case)
        Unit("n/a", "n/a", arrayListOf("n/a"), baseConverter)
    )

    val distance = Category(allUnits, "distance", "meter")
    val mass = Category(allUnits, "mass", "gram")
    val time = Category(allUnits, "time", "second")
    val temperature = Category(allUnits, "temperature", "celsius")

    val categories = listOf(
        distance.tag,
//        "cooking", Create this categories based on tags from other categories
//        "electric current",
//        "luminous intensity",
        mass.tag,
        temperature.tag,
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


    fun defaultCategory(): String {
        return categories.first()
    }

    fun setCategory(category: String): Category {
        return when(category) {
            "distance" -> distance
//            "electric current" -> electricCurrentUnits
//            "luminous intensity" -> luminousIntensityUnits
            "mass" -> mass
            "temperature" -> temperature
            "time" -> time
            else -> {
                Log.e("ERROR:", "Unknown category $category passed in!")
                Category(allUnits, "n/a", "n/a")
            }
        }
    }
}
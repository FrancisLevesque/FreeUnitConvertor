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
    private val tonConverter = FactorConverter(BigDecimal.valueOf(907184.74))

    private val teaspoonConverter = FactorConverter(BigDecimal.valueOf(0.005))
    private val tablespoonConverter = FactorConverter(BigDecimal.valueOf(0.015))
    private val cupConverter = FactorConverter(BigDecimal.valueOf(0.25))
    private val usCupConverter = FactorConverter(BigDecimal.valueOf(0.2365882365))

    private val fluidOunceConverter = FactorConverter(BigDecimal.valueOf(0.030))
    private val cubicInchConverter = FactorConverter(BigDecimal.valueOf(0.016387064))
    private val cubicFootConverter = FactorConverter(BigDecimal.valueOf(28.316864592))
    private val pintConverter = FactorConverter(BigDecimal.valueOf(0.5))
    private val usPintConverter = FactorConverter(BigDecimal.valueOf(0.473176473))
    private val imperialPintConverter = FactorConverter(BigDecimal.valueOf(0.568))
    private val quartConverter = FactorConverter(BigDecimal.valueOf(0.94635295))
    private val usGallonConverter = FactorConverter(BigDecimal.valueOf(3.785411784))

    private val minuteConverter = FactorConverter(BigDecimal.valueOf(60.0))
    private val hourConverter = FactorConverter(BigDecimal.valueOf(3600.0))
    private val dayConverter = FactorConverter(BigDecimal.valueOf(86400.0))
    private val weekConverter = FactorConverter(BigDecimal.valueOf(604800.0))
    private val monthConverter = FactorConverter(BigDecimal.valueOf(2629800.0))
    private val yearConverter = FactorConverter(BigDecimal.valueOf(31557600.0))

    private val kelvinConverter = StepConverter(BigDecimal.valueOf(273.15))

    private val allUnits = arrayListOf<Unit>(
        // DISTANCE
        Unit("Nanometer", "nm", arrayListOf("Distance"), nanoConverter),
        Unit("Micrometer", "μm", arrayListOf("Distance"), microConverter),
        Unit("Millimeter", "mm", arrayListOf("Distance"), milliConverter),
        Unit("Centimeter", "cm", arrayListOf("Distance"), centiConverter),
        Unit("Decimeter", "dm", arrayListOf("Distance"), deciConverter),
        Unit("Meter", "m", arrayListOf("Distance"), baseConverter),
        Unit("Kilometer", "km", arrayListOf("Distance"), kiloConverter),
        Unit("Inch", "in", arrayListOf("Distance"), inchConverter),
        Unit("Foot", "ft", arrayListOf("Distance"), footConverter),
        Unit("Yard", "yd", arrayListOf("Distance"), yardConverter),
        Unit("Mile", "mi", arrayListOf("Distance"), mileConverter),
        Unit("Fathom", "mi", arrayListOf("Distance"), fathomConverter),
        Unit("Nautical mile", "nmi", arrayListOf("Distance"), nauticalMileConverter),
        // MASS
        Unit("Milligram", "mg", arrayListOf("Mass", "Weight", "Cooking"), milliConverter),
        Unit("Gram", "g", arrayListOf("Mass", "Weight", "Cooking"), gramConverter),
        Unit("Kilogram", "kg", arrayListOf("Mass", "Weight", "Cooking"), kiloConverter),
        Unit("Tonnes", "t", arrayListOf("Mass", "Weight"), megaConverter),
        Unit("Ounce", "oz", arrayListOf("Mass", "Cooking"), ounceConverter),
        Unit("Pound", "lb", arrayListOf("Mass", "Cooking"), poundConverter),
        Unit("US Ton", "T", arrayListOf("Mass"), tonConverter),
        // COOKING
        Unit("Teaspoon", "tsp", arrayListOf("Cooking"), teaspoonConverter),
        Unit("Tablespoon", "tbsp", arrayListOf("Cooking"), tablespoonConverter),
        Unit("Metric Cup", "c", arrayListOf("Cooking"), cupConverter),
        Unit("US Cup", "C", arrayListOf("Cooking"), usCupConverter),
        // VOLUME
        Unit("Milliliter", "ml", arrayListOf("Volume", "Cooking"), milliConverter),
        Unit("Centiliter", "cl", arrayListOf("Volume", "Cooking"), centiConverter),
        Unit("Deciliter", "dl", arrayListOf("Volume", "Cooking"), deciConverter),
        Unit("Liter", "l", arrayListOf("Volume", "Cooking"), baseConverter),
        Unit("Cubic Meter", "m3", arrayListOf("Volume"), kiloConverter),
        Unit("Cubic Inch", "in3", arrayListOf("Volume"), cubicInchConverter),
        Unit("Cubic Foot", "ft3", arrayListOf("Volume"), cubicFootConverter),
        Unit("Fluid Ounce", "fl oz", arrayListOf("Volume", "Cooking"), fluidOunceConverter),
        Unit("Pint", "p", arrayListOf("Volume", "Cooking"), pintConverter),
        Unit("US Pint", "pt", arrayListOf("Volume", "Cooking"), usPintConverter),
        Unit("Imperial Pint", "pt", arrayListOf("Volume", "Cooking"), imperialPintConverter),
        Unit("Quart", "qt", arrayListOf("Volume", "Cooking"), quartConverter),
        Unit("US Gallon", "gal", arrayListOf("Volume", "Cooking"), usGallonConverter),
        // TEMPERATURE
        Unit("Kelvin", "K", arrayListOf("Temperature"), kelvinConverter),
        Unit("Celsius", "°C", arrayListOf("Temperature"), baseConverter),
        Unit("Fahrenheit", "°F", arrayListOf("Temperature"), FahrenheitConverter()),
        // TIME
        Unit("Nanosecond", "ns", arrayListOf("Time"), nanoConverter),
        Unit("Microsecond", "μs", arrayListOf("Time"), microConverter),
        Unit("Millisecond", "ms", arrayListOf("Time"), milliConverter),
        Unit("Second", "s", arrayListOf("Time"), baseConverter),
        Unit("Minute", "min", arrayListOf("Time"), minuteConverter),
        Unit("Hour", "h", arrayListOf("Time"), hourConverter),
        Unit("Day", "d", arrayListOf("Time"), dayConverter),
        Unit("Week", "w", arrayListOf("Time"), weekConverter),
        Unit("Month", "mo", arrayListOf("Time"), monthConverter),
        Unit("Year", "yr", arrayListOf("Time"), yearConverter),
        // EMPTY (for the error case)
        Unit("N/A", "N/A", arrayListOf("N/A"), baseConverter)
    )

    val cooking = Category(allUnits, "Cooking", "Gram")
    val distance = Category(allUnits, "Distance", "Meter")
    val mass = Category(allUnits, "Mass", "Gram")
    val temperature = Category(allUnits, "Temperature", "Celsius")
    val time = Category(allUnits, "Time", "Second")
    val volume = Category(allUnits, "Volume", "Liter")

    val categories = listOf(
        cooking.tag,
        distance.tag,
        mass.tag,
        temperature.tag,
        time.tag,
        volume.tag
    )

    fun defaultCategory(): String {
        return categories.first()
    }

    fun setCategory(category: String): Category {
        return when(category) {
            "Cooking" -> cooking
            "Distance" -> distance
            "Mass" -> mass
            "Temperature" -> temperature
            "Time" -> time
            "Volume" -> volume
            else -> {
                Log.e("ERROR:", "Unknown category $category passed in!")
                Category(allUnits, "N/A", "N/A")
            }
        }
    }
}
package com.francislevesque.freeunitconverter.model

import android.util.Log
import java.math.BigDecimal

object Units {
    private val baseConverter = FactorConverter(BigDecimal.valueOf(1.0))

    private val squareInchConverter = FactorConverter(BigDecimal.valueOf(0.00064516))
    private val squareFootConverter = FactorConverter(BigDecimal.valueOf(0.09290304))
    private val squareYardConverter = FactorConverter(BigDecimal.valueOf(0.83612736))
    private val squareMileConverter = FactorConverter(BigDecimal.valueOf(2589988.1103))
    private val hectareConverter = FactorConverter(BigDecimal.valueOf(10000.0))
    private val acreConverter = FactorConverter(BigDecimal.valueOf(4046.8564224))

    private val nanoConverter = FactorConverter(BigDecimal.valueOf(0.000000001))
    private val microConverter = FactorConverter(BigDecimal.valueOf(0.000001))
    private val milliConverter = FactorConverter(BigDecimal.valueOf(0.001))
    private val centiConverter = FactorConverter(BigDecimal.valueOf(0.01))
    private val deciConverter = FactorConverter(BigDecimal.valueOf(0.1))
    private val kiloConverter = FactorConverter(BigDecimal.valueOf(1000.0))
    private val kibiConverter = FactorConverter(BigDecimal.valueOf(1024.0))
    private val megaConverter = FactorConverter(BigDecimal.valueOf(1000000.0))
    private val mebiConverter = FactorConverter(BigDecimal.valueOf(1048576.0))
    private val gigaConverter = FactorConverter(BigDecimal.valueOf(1000000000.0))
    private val gibiConverter = FactorConverter(BigDecimal.valueOf(1073741824.0))
    private val teraConverter = FactorConverter(BigDecimal.valueOf(1000000000000.0))
    private val tebiConverter = FactorConverter(BigDecimal.valueOf(1099511627776.0))
    private val petaConverter = FactorConverter(BigDecimal.valueOf(1000000000000000.0))
    private val pebiConverter = FactorConverter(BigDecimal.valueOf(1125899906842624.0))

    private val bitConverter = FactorConverter(BigDecimal.valueOf(0.125))
    private val kiloBitConverter = FactorConverter(BigDecimal.valueOf(125))
    private val kibiBitConverter = FactorConverter(BigDecimal.valueOf(128))
    private val megaBitConverter = FactorConverter(BigDecimal.valueOf(125000))
    private val mebiBitConverter = FactorConverter(BigDecimal.valueOf(131072))
    private val gigaBitConverter = FactorConverter(BigDecimal.valueOf(125000000))
    private val gibiBitConverter = FactorConverter(BigDecimal.valueOf(134217728))
    private val teraBitConverter = FactorConverter(BigDecimal.valueOf(125000000000))
    private val tebiBitConverter = FactorConverter(BigDecimal.valueOf(137438953472))
    private val petaBitConverter = FactorConverter(BigDecimal.valueOf(125000000000000))
    private val pebiBitConverter = FactorConverter(BigDecimal.valueOf(140737488355328))

    private val litersPer100KilometersConverter = InvertedFactorConverter(BigDecimal.valueOf(100.0))
    private val milesPerUSGallonConverter = ReverseFactorConverter(BigDecimal.valueOf(2.352145833))
    private val milesPerImperialGallonConverter = FactorConverter(BigDecimal.valueOf(0.3540061899346471))

    private val inchConverter = FactorConverter(BigDecimal.valueOf(0.0254))
    private val footConverter = FactorConverter(BigDecimal.valueOf(0.3048))
    private val yardConverter = FactorConverter(BigDecimal.valueOf(0.9144))
    private val mileConverter = FactorConverter(BigDecimal.valueOf(1609.344))
    private val fathomConverter = FactorConverter(BigDecimal.valueOf(1.8288))
    private val nauticalMileConverter = FactorConverter(BigDecimal.valueOf(1852.0))

    private val ounceConverter = FactorConverter(BigDecimal.valueOf(28.34952))
    private val poundConverter = FactorConverter(BigDecimal.valueOf(453.59237))
    private val tonConverter = FactorConverter(BigDecimal.valueOf(907184.74))
    private val teaspoonWeightConverter = FactorConverter(BigDecimal.valueOf(5))
    private val tablespoonWeightConverter = FactorConverter(BigDecimal.valueOf(15))

    private val teaspoonVolumeConverter = FactorConverter(BigDecimal.valueOf(0.005))
    private val tablespoonVolumeConverter = FactorConverter(BigDecimal.valueOf(0.015))
    private val cupVolumeConverter = FactorConverter(BigDecimal.valueOf(0.25))
    private val usLegalCupVolumeConverter = FactorConverter(BigDecimal.valueOf(0.24))
    private val fluidOunceConverter = FactorConverter(BigDecimal.valueOf(0.030))
    private val cubicInchConverter = FactorConverter(BigDecimal.valueOf(0.016387064))
    private val cubicFootConverter = FactorConverter(BigDecimal.valueOf(28.316864592))
    private val pintConverter = FactorConverter(BigDecimal.valueOf(0.5))
    private val usPintConverter = FactorConverter(BigDecimal.valueOf(0.473176473))
    private val imperialPintConverter = FactorConverter(BigDecimal.valueOf(0.568))
    private val quartConverter = FactorConverter(BigDecimal.valueOf(0.94635295))
    private val usGallonConverter = FactorConverter(BigDecimal.valueOf(3.785411784))
    private val imperialGallonConverter = FactorConverter(BigDecimal.valueOf(4.54609))

    private val psiConverter = FactorConverter(BigDecimal.valueOf(0.00014503773773))
    private val atmosphereConverter = FactorConverter(BigDecimal.valueOf(101325.0))

    private val kmhConverter = ReverseFactorConverter(BigDecimal.valueOf(3.6))
    private val ftsConverter = FactorConverter(BigDecimal.valueOf(0.3048))
    private val mphConverter = FactorConverter(BigDecimal.valueOf(0.44704))
    private val knotConverter = ReverseFactorConverter(BigDecimal.valueOf(1.943844))

    private val kelvinConverter = StepConverter(BigDecimal.valueOf(273.15))

    private val minuteConverter = FactorConverter(BigDecimal.valueOf(60.0))
    private val hourConverter = FactorConverter(BigDecimal.valueOf(3600.0))
    private val dayConverter = FactorConverter(BigDecimal.valueOf(86400.0))
    private val weekConverter = FactorConverter(BigDecimal.valueOf(604800.0))
    private val monthConverter = FactorConverter(BigDecimal.valueOf(2629800.0))
    private val yearConverter = FactorConverter(BigDecimal.valueOf(31557600.0))

    private val allUnits = arrayListOf<Unit>(
        // AREA
        Unit("Square meter", "m2", arrayListOf("Area"), baseConverter),
        Unit("Square kilometer", "km2", arrayListOf("Area"), megaConverter),
        Unit("Square inch", "in2", arrayListOf("Area"), squareInchConverter),
        Unit("Square foot", "ft2", arrayListOf("Area"), squareFootConverter),
        Unit("Square yard", "yd2", arrayListOf("Area"), squareYardConverter),
        Unit("Square mile", "mi2", arrayListOf("Area"), squareMileConverter),
        Unit("Hectare", "ha", arrayListOf("Area"), hectareConverter),
        Unit("Acre", "ac", arrayListOf("Area"), acreConverter),
        // COMPUTING
        Unit("Bit", "b", arrayListOf("Computing"), bitConverter),
        Unit("Kilobit", "kbit", arrayListOf("Computing"), kiloBitConverter),
        Unit("Kibibit", "Kibit", arrayListOf("Computing"), kibiBitConverter),
        Unit("Megabit", "Mbit", arrayListOf("Computing"), megaBitConverter),
        Unit("Megbibit", "Mibit", arrayListOf("Computing"), mebiBitConverter),
        Unit("Gigabit", "Gbit", arrayListOf("Computing"), gigaBitConverter),
        Unit("Gibibit", "Gibit", arrayListOf("Computing"), gibiBitConverter),
        Unit("Terabit", "Tbit", arrayListOf("Computing"), teraBitConverter),
        Unit("Tebibit", "Tibit", arrayListOf("Computing"), tebiBitConverter),
        Unit("Petabit", "Pbit", arrayListOf("Computing"), petaBitConverter),
        Unit("Pebibit", "Pibit", arrayListOf("Computing"), pebiBitConverter),
        Unit("Byte", "B", arrayListOf("Computing"), baseConverter),
        Unit("Kilobyte", "kB", arrayListOf("Computing"), kiloConverter),
        Unit("Kibibyte", "KiB", arrayListOf("Computing"), kibiConverter),
        Unit("Megabyte", "MB", arrayListOf("Computing"), megaConverter),
        Unit("Megbibyte", "MiB", arrayListOf("Computing"), mebiConverter),
        Unit("Gigabyte", "GB", arrayListOf("Computing"), gigaConverter),
        Unit("Gibibyte", "GiB", arrayListOf("Computing"), gibiConverter),
        Unit("Terabyte", "TB", arrayListOf("Computing"), teraConverter),
        Unit("Tebibyte", "TiB", arrayListOf("Computing"), tebiConverter),
        Unit("Petabyte", "PB", arrayListOf("Computing"), petaConverter),
        Unit("Pebibyte", "PiB", arrayListOf("Computing"), pebiConverter),
        // DATA TRANSFER RATE
        Unit("Bit per second", "b/s", arrayListOf("Data Transfer Rate"), bitConverter),
        Unit("Byte per second", "B/s", arrayListOf("Data Transfer Rate"), baseConverter),
        Unit("Kilobit per second", "kbit/s", arrayListOf("Data Transfer Rate"), kiloBitConverter),
        Unit("Kibibit per second", "Kibit/s", arrayListOf("Data Transfer Rate"), kibiBitConverter),
        Unit("Kilobyte per second", "kB/s", arrayListOf("Data Transfer Rate"), kiloConverter),
        Unit("Megabit per second", "Mbit/s", arrayListOf("Data Transfer Rate"), megaBitConverter),
        Unit("Megbibit per second", "Mibit/s", arrayListOf("Data Transfer Rate"), mebiBitConverter),
        Unit("Megabyte per second", "MB/s", arrayListOf("Data Transfer Rate"), megaConverter),
        Unit("Gigabit per second", "Gbit/s", arrayListOf("Data Transfer Rate"), gigaBitConverter),
        Unit("Gibibit per second", "Gibit/s", arrayListOf("Data Transfer Rate"), gibiBitConverter),
        Unit("Gigabyte per second", "GB/s", arrayListOf("Data Transfer Rate"), gigaConverter),
        Unit("Terabit per second", "Tbit/s", arrayListOf("Data Transfer Rate"), teraBitConverter),
        Unit("Tebibit per second", "Tibit/s", arrayListOf("Data Transfer Rate"), tebiBitConverter),
        Unit("Terabyte per second", "TB/s", arrayListOf("Data Transfer Rate"), teraConverter),
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
        Unit("Fathom", "ftm", arrayListOf("Distance"), fathomConverter),
        Unit("Nautical mile", "nmi", arrayListOf("Distance"), nauticalMileConverter),
        // FREQUENCY
        Unit("Hertz", "Hz", arrayListOf("Frequency"), baseConverter),
        Unit("Kilohertz", "kHz", arrayListOf("Frequency"), kiloConverter),
        Unit("Megahertz", "MHz", arrayListOf("Frequency"), megaConverter),
        Unit("Gigahertz", "GHz", arrayListOf("Frequency"), gigaConverter),
        // FUEL ECONOMY
        Unit("Kilometer per liter", "km/L", arrayListOf("Fuel Economy"), baseConverter),
        Unit("Liters per 100 kilometers", "L/100km", arrayListOf("Fuel Economy"), litersPer100KilometersConverter),
        Unit("Miles per US Gallon", "mpg", arrayListOf("Fuel Economy"), milesPerUSGallonConverter),
        Unit("Miles per Imperial Gallon", "mpg", arrayListOf("Fuel Economy"), milesPerImperialGallonConverter),
        // MASS
        Unit("Milligram", "mg", arrayListOf("Mass", "Weight"), milliConverter),
        Unit("Gram", "g", arrayListOf("Mass", "Weight"), baseConverter),
        Unit("Kilogram", "kg", arrayListOf("Mass", "Weight"), kiloConverter),
        Unit("Tonnes", "t", arrayListOf("Mass", "Weight"), megaConverter),
        Unit("Teaspoon", "tsp", arrayListOf("Mass", "Weight"), teaspoonWeightConverter),
        Unit("Tablespoon", "tbsp", arrayListOf("Mass", "Weight"), tablespoonWeightConverter),
        Unit("Ounce", "oz", arrayListOf("Mass"), ounceConverter),
        Unit("Pound", "lb", arrayListOf("Mass"), poundConverter),
        Unit("US Ton", "T", arrayListOf("Mass"), tonConverter),
        // PRESSURE
        Unit("Pascal", "Pa", arrayListOf("Pressure"), baseConverter),
        Unit("Kilopascal", "kPa", arrayListOf("Pressure"), kiloConverter),
        Unit("Pound-force per square inch", "psi", arrayListOf("Pressure"), psiConverter),
        Unit("Standard atmosphere", "atm", arrayListOf("Pressure"), atmosphereConverter),
        // SPEED
        Unit("Meters per second", "m/s", arrayListOf("Speed"), baseConverter),
        Unit("Kilometers per hour", "km/h", arrayListOf("Speed"), kmhConverter),
        Unit("Feet per second", "ft/s", arrayListOf("Speed"), ftsConverter),
        Unit("Miles per hour", "mph", arrayListOf("Speed"), mphConverter),
        Unit("Knot", "kn", arrayListOf("Speed"), knotConverter),
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
        // VOLUME
        Unit("Milliliter", "ml", arrayListOf("Volume"), milliConverter),
        Unit("Centiliter", "cl", arrayListOf("Volume"), centiConverter),
        Unit("Deciliter", "dl", arrayListOf("Volume"), deciConverter),
        Unit("Liter", "l", arrayListOf("Volume"), baseConverter),
        Unit("Teaspoon", "tsp", arrayListOf("Volume"), teaspoonVolumeConverter),
        Unit("Tablespoon", "tbsp", arrayListOf("Volume"), tablespoonVolumeConverter),
        Unit("Metric Cup", "c", arrayListOf("Volume"), cupVolumeConverter),
        Unit("US Legal Cup", "C", arrayListOf("Volume"), usLegalCupVolumeConverter),
        Unit("Cubic Meter", "m3", arrayListOf("Volume"), kiloConverter),
        Unit("Cubic Inch", "in3", arrayListOf("Volume"), cubicInchConverter),
        Unit("Cubic Foot", "ft3", arrayListOf("Volume"), cubicFootConverter),
        Unit("Fluid Ounce", "fl oz", arrayListOf("Volume"), fluidOunceConverter),
        Unit("Pint", "p", arrayListOf("Volume"), pintConverter),
        Unit("US Pint", "pt", arrayListOf("Volume"), usPintConverter),
        Unit("Imperial Pint", "pt", arrayListOf("Volume"), imperialPintConverter),
        Unit("Quart", "qt", arrayListOf("Volume"), quartConverter),
        Unit("US Gallon", "gal", arrayListOf("Volume"), usGallonConverter),
        Unit("Imperial Gallon", "gal", arrayListOf("Volume"), imperialGallonConverter),
        // EMPTY (for the error case)
        Unit("N/A", "N/A", arrayListOf("N/A"), baseConverter)
    )

    // WARNING: When creating SHARED categories they MUST have the SAME base unit!!
    // TODO: Add check for this
    val area = Category(allUnits, "Area", "Square meter")
    val computing = Category(allUnits, "Computing", "Byte")
    val frequency = Category(allUnits, "Frequency", "Hertz")
    val fuelEconomy = Category(allUnits, "Fuel Economy", "Kilometer per liter")
    val dataTransferRate = Category(allUnits, "Data Transfer Rate", "Bit per second")
    val distance = Category(allUnits, "Distance", "Meter")
    val mass = Category(allUnits, "Mass", "Gram")
    val pressure = Category(allUnits, "Pressure", "Pascal")
    val speed = Category(allUnits, "Speed", "Meters per second")
    val temperature = Category(allUnits, "Temperature", "Celsius")
    val time = Category(allUnits, "Time", "Second")
    val volume = Category(allUnits, "Volume", "Liter")

    val categories = listOf(
        area.toString(),
        computing.toString(),
        frequency.toString(),
        fuelEconomy.toString(),
        dataTransferRate.toString(),
        distance.toString(),
        mass.toString(),
        pressure.toString(),
        speed.toString(),
        temperature.toString(),
        time.toString(),
        volume.toString()
    )

    fun defaultCategory(): String {
        return categories.first()
    }

    fun getCategoryFromName(category: String): Category {
        return when(category) {
            "Area" -> area
            "Computing" -> computing
            "Frequency" -> frequency
            "Fuel Economy" -> fuelEconomy
            "Data Transfer Rate" -> dataTransferRate
            "Distance" -> distance
            "Mass" -> mass
            "Pressure" -> pressure
            "Speed" -> speed
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
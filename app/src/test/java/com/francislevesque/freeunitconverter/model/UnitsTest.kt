package com.francislevesque.freeunitconverter.model

import org.junit.jupiter.api.Assertions.*
import java.math.BigDecimal

internal class UnitsTest {
    @org.junit.jupiter.api.Test
    fun area_squareMeterToSquareKilometer() {
        val squareMeter = Units.area.getUnit("Square meter")
        val squareKilometer = Units.area.getUnit("Square kilometer")
        val result = squareMeter.convert(BigDecimal.valueOf(-23497.74), squareKilometer, 8)
        assertEquals(-0.02349774, result.toDouble())
    }
    @org.junit.jupiter.api.Test
    fun area_squareMeterToSquareMile() {
        val squareMeter = Units.area.getUnit("Square meter")
        val squareMile = Units.area.getUnit("Square mile")
        val result = squareMeter.convert(BigDecimal.valueOf(100020), squareMile, 10)
        assertEquals(0.0386179379, result.toDouble())
    }
    @org.junit.jupiter.api.Test
    fun area_squareInchToSquareMeter() {
        val squareInch = Units.area.getUnit("Square inch")
        val squareMeter = Units.area.getUnit("Square meter")
        val result = squareInch.convert(BigDecimal.valueOf(300), squareMeter, 6)
        assertEquals(0.193548, result.toDouble())
    }
    @org.junit.jupiter.api.Test
    fun area_hectareToAcre() {
        val hectare = Units.area.getUnit("Hectare")
        val acre = Units.area.getUnit("Acre")
        val result = hectare.convert(BigDecimal.valueOf(23.9), acre, 9)
        assertEquals(59.058186171, result.toDouble())
    }
    @org.junit.jupiter.api.Test
    fun area_squareMileToSquareYard() {
        val squareMile = Units.area.getUnit("Square mile")
        val squareYard = Units.area.getUnit("Square yard")
        val result = squareMile.convert(BigDecimal.valueOf(0.003209), squareYard, 5)
        assertEquals(9940.1984, result.toDouble())
    }
    @org.junit.jupiter.api.Test
    fun area_squareFootToSquareInch() {
        val squareFoot = Units.area.getUnit("Square foot")
        val squareInch = Units.area.getUnit("Square inch")
        val result = squareFoot.convert(BigDecimal.valueOf(900.311), squareInch, 6)
        assertEquals(129644.784, result.toDouble())
    }

    @org.junit.jupiter.api.Test
    fun computing_byteToKibibit() {
        val byte = Units.computing.getUnit("Byte")
        val kibibit = Units.computing.getUnit("Kibibit")
        val result = byte.convert(BigDecimal.valueOf(39845), kibibit, 5)
        assertEquals(311.28906, result.toDouble())
    }
    @org.junit.jupiter.api.Test
    fun computing_petabyteToByte() {
        val petabyte = Units.computing.getUnit("Petabyte")
        val byte = Units.computing.getUnit("Byte")
        val result = petabyte.convert(BigDecimal.valueOf(0.0056), byte, 2)
        assertEquals(5600000000000.0, result.toDouble())
    }
    @org.junit.jupiter.api.Test
    fun computing_tebibitToMillimeter() {
        val tebibit = Units.computing.getUnit("Tebibit")
        val gigabyte = Units.computing.getUnit("Gigabyte")
        val result = tebibit.convert(BigDecimal.valueOf(12.457), gigabyte, 5)
        assertEquals(1712.07704, result.toDouble())
    }

    @org.junit.jupiter.api.Test
    fun dataTransferRate_bytePerSecondToKibibitPerSecond() {
        val bytePerSecond = Units.dataTransferRate.getUnit("Byte per second")
        val kibibitPerSecond = Units.dataTransferRate.getUnit("Kibibit per second")
        val result = bytePerSecond.convert(BigDecimal.valueOf(-1111), kibibitPerSecond, 5)
        assertEquals(-8.67969, result.toDouble())
    }
    @org.junit.jupiter.api.Test
    fun dataTransferRate_gigabitPerSecondToBytePerSecond() {
        val gigabitPerSecond = Units.dataTransferRate.getUnit("Gigabit per second")
        val bytePerSecond = Units.dataTransferRate.getUnit("Byte per second")
        val result = gigabitPerSecond.convert(BigDecimal.valueOf(0.000738), bytePerSecond, 2)
        assertEquals(92250.0, result.toDouble())
    }
    @org.junit.jupiter.api.Test
    fun dataTransferRate_kibibitPerSecondToTebibitPerSecond() {
        val kibibitPerSecond = Units.dataTransferRate.getUnit("Kibibit per second")
        val tebibitPerSecond = Units.dataTransferRate.getUnit("Tebibit per second")
        val result = kibibitPerSecond.convert(BigDecimal.valueOf(568790.3), tebibitPerSecond, 14)
        assertEquals(0.00052972724661, result.toDouble())
    }

    @org.junit.jupiter.api.Test
    fun distance_meterToFoot() {
        val meter = Units.distance.getUnit("Meter")
        val foot = Units.distance.getUnit("Foot")
        val result = meter.convert(BigDecimal.valueOf(456.22), foot, 5)
        assertEquals(1496.78478, result.toDouble())
    }
    @org.junit.jupiter.api.Test
    fun distance_mileToMeter() {
        val mile = Units.distance.getUnit("Mile")
        val meter = Units.distance.getUnit("Meter")
        val result = mile.convert(BigDecimal.valueOf(1), meter, 2)
        assertEquals(1609.34, result.toDouble())
    }
    @org.junit.jupiter.api.Test
    fun distance_yardToMillimeter() {
        val yard = Units.distance.getUnit("Yard")
        val millimeter = Units.distance.getUnit("Millimeter")
        val result = yard.convert(BigDecimal.valueOf(0.09812), millimeter, 7)
        assertEquals(89.720928, result.toDouble())
    }

    @org.junit.jupiter.api.Test
    fun frequency_hertzToKilohertz() {
        val hertz = Units.frequency.getUnit("Hertz")
        val kilohertz = Units.frequency.getUnit("Kilohertz")
        val result = hertz.convert(BigDecimal.valueOf(319.4), kilohertz, 4)
        assertEquals(0.3194, result.toDouble())
    }
    @org.junit.jupiter.api.Test
    fun frequency_megahertzToHertz() {
        val megahertz = Units.frequency.getUnit("Megahertz")
        val hertz = Units.frequency.getUnit("Hertz")
        val result = megahertz.convert(BigDecimal.valueOf(0.004091), hertz, 1)
        assertEquals(4091.0, result.toDouble())
    }

    @org.junit.jupiter.api.Test
    fun mass_gramToPound() {
        val gram = Units.mass.getUnit("Gram")
        val pound = Units.mass.getUnit("Pound")
        val result = gram.convert(BigDecimal.valueOf(456.09842), pound, 11)
        assertEquals(1.00552489452, result.toDouble())
    }
    @org.junit.jupiter.api.Test
    fun mass_milligramToGram() {
        val milligram = Units.mass.getUnit("Milligram")
        val gram = Units.mass.getUnit("Gram")
        val result = milligram.convert(BigDecimal.valueOf(00.12), gram, 5)
        assertEquals(0.00012, result.toDouble())
    }
    @org.junit.jupiter.api.Test
    fun mass_ounceToKilogram() {
        val ounce = Units.mass.getUnit("Ounce")
        val kilogram = Units.mass.getUnit("Kilogram")
        val result = ounce.convert(BigDecimal.valueOf(2134), kilogram, 5)
        assertEquals(60.49788, result.toDouble())
    }

    @org.junit.jupiter.api.Test
    fun time_secondToWeek() {
        val second = Units.time.getUnit("Second")
        val week = Units.time.getUnit("Week")
        val result = second.convert(BigDecimal.valueOf(234354600), week, 9)
        assertEquals(387.491071429, result.toDouble())
    }
    @org.junit.jupiter.api.Test
    fun time_dayToSecond() {
        val day = Units.time.getUnit("Day")
        val second = Units.time.getUnit("Second")
        val result = day.convert(BigDecimal.valueOf(0.056), second, 3)
        assertEquals(4838.4, result.toDouble())
    }
    @org.junit.jupiter.api.Test
    fun time_minuteToMicrosecond() {
        val minute = Units.time.getUnit("Minute")
        val microsecond = Units.time.getUnit("Microsecond")
        val result = minute.convert(BigDecimal.valueOf(.890), microsecond, 5)
        assertEquals(5.34e+7, result.toDouble())
    }

    @org.junit.jupiter.api.Test
    fun temperature_celsiusToFahrenheit() {
        val celsius = Units.temperature.getUnit("Celsius")
        val fahrenheit = Units.temperature.getUnit("Fahrenheit")
        val result = celsius.convert(BigDecimal.valueOf(100), fahrenheit, 2)
        assertEquals(212.0, result.toDouble())
    }
    @org.junit.jupiter.api.Test
    fun temperature_fahrenheitToCelsius() {
        val fahrenheit = Units.temperature.getUnit("Fahrenheit")
        val celsius = Units.temperature.getUnit("Celsius")
        val result = fahrenheit.convert(BigDecimal.valueOf(212), celsius, 2)
        assertEquals(100.0, result.toDouble())
    }

    @org.junit.jupiter.api.Test
    fun setCategory() {
        assertEquals(Units.distance, Units.getCategoryFromName("Distance"))
        assertEquals(Units.mass, Units.getCategoryFromName("Mass"))
        assertEquals(Units.time, Units.getCategoryFromName("Time"))
    }
}
package com.francislevesque.freeunitconverter.model

import org.junit.jupiter.api.Assertions.*
import java.math.BigDecimal

internal class UnitsTest {
    @org.junit.jupiter.api.Test
    fun distance_meterToFoot() {
        val meter = Units.distance.getUnit("meter")
        val foot = Units.distance.getUnit("foot")
        val result = meter.convert(BigDecimal.valueOf(456.22), foot, 5)
        assertEquals(1496.78478, result.toDouble())
    }
    @org.junit.jupiter.api.Test
    fun distance_mileToMeter() {
        val mile = Units.distance.getUnit("mile")
        val meter = Units.distance.getUnit("meter")
        val result = mile.convert(BigDecimal.valueOf(1), meter, 2)
        assertEquals(1609.34, result.toDouble())
    }
    @org.junit.jupiter.api.Test
    fun distance_yardToMillimeter() {
        val yard = Units.distance.getUnit("yard")
        val millimeter = Units.distance.getUnit("millimeter")
        val result = yard.convert(BigDecimal.valueOf(0.09812), millimeter, 7)
        assertEquals(89.720928, result.toDouble())
    }

    @org.junit.jupiter.api.Test
    fun mass_gramToPound() {
        val gram = Units.mass.getUnit("gram")
        val pound = Units.mass.getUnit("pound")
        val result = gram.convert(BigDecimal.valueOf(456.09842), pound, 11)
        assertEquals(1.00552489452, result.toDouble())
    }
    @org.junit.jupiter.api.Test
    fun mass_milligramToGram() {
        val milligram = Units.mass.getUnit("milligram")
        val gram = Units.mass.getUnit("gram")
        val result = milligram.convert(BigDecimal.valueOf(00.12), gram, 5)
        assertEquals(0.00012, result.toDouble())
    }
    @org.junit.jupiter.api.Test
    fun mass_ounceToKilogram() {
        val ounce = Units.mass.getUnit("ounce")
        val kilogram = Units.mass.getUnit("kilogram")
        val result = ounce.convert(BigDecimal.valueOf(2134), kilogram, 5)
        assertEquals(60.49788, result.toDouble())
    }

    @org.junit.jupiter.api.Test
    fun time_secondToWeek() {
        val second = Units.time.getUnit("second")
        val week = Units.time.getUnit("week")
        val result = second.convert(BigDecimal.valueOf(234354600), week, 9)
        assertEquals(387.491071429, result.toDouble())
    }
    @org.junit.jupiter.api.Test
    fun time_dayToSecond() {
        val day = Units.time.getUnit("day")
        val second = Units.time.getUnit("second")
        val result = day.convert(BigDecimal.valueOf(0.056), second, 3)
        assertEquals(4838.4, result.toDouble())
    }
    @org.junit.jupiter.api.Test
    fun time_minuteToMicrosecond() {
        val minute = Units.time.getUnit("minute")
        val microsecond = Units.time.getUnit("microsecond")
        val result = minute.convert(BigDecimal.valueOf(.890), microsecond, 5)
        assertEquals(5.34e+7, result.toDouble())
    }

    @org.junit.jupiter.api.Test
    fun setCategory() {
        assertEquals(Units.distance, Units.setCategory("distance"))
        assertEquals(Units.mass, Units.setCategory("mass"))
        assertEquals(Units.time, Units.setCategory("time"))
    }
}
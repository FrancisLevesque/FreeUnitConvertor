package com.francislevesque.freeunitconverter.model

import java.math.BigDecimal
import java.math.RoundingMode

class FahrenheitConverter() : FactorConverter(BigDecimal.valueOf(0)) {
    private val five: BigDecimal = BigDecimal.valueOf(5.0)
    private val nine: BigDecimal = BigDecimal.valueOf(9.0)
    private val thirtyTwo: BigDecimal = BigDecimal.valueOf(32.0)

    // Fahrenheit to Celsius
    // [°C] = ([°F] − 32) × ​5⁄9
    override fun toBase(input: BigDecimal, precision: Int): BigDecimal {
        val fiveOverNine = five.divide(nine, 20, RoundingMode.HALF_EVEN)
        return (input.subtract(thirtyTwo)).multiply(fiveOverNine)
    }

    // Celsius to Fahrenheit
    // [°F] = [°C] × ​9⁄5 + 32
    override fun fromBase(input: BigDecimal, precision: Int): BigDecimal {
        val nineOverFive = nine.divide(five)
        val result = (input.multiply(nineOverFive)).add(thirtyTwo)
        return result.setScale(precision, RoundingMode.HALF_EVEN).stripTrailingZeros()
    }
}
package com.francislevesque.freeunitconverter.model

import java.math.BigDecimal
import java.math.RoundingMode

class ReverseFactorConverter(private val inverseFactor: BigDecimal) : FactorConverter(inverseFactor) {
    override fun toBase(input: BigDecimal, precision: Int): BigDecimal {
        println("DIVIDING $input by $inverseFactor")
        val result = input.divide(inverseFactor, 20, RoundingMode.HALF_EVEN)
        println("RESULT: $result")
        return result
    }

    override fun fromBase(input: BigDecimal, precision: Int): BigDecimal {
        println("MULTIPLYING $input by $inverseFactor")
        val result = input.multiply(inverseFactor)
        return result.setScale(precision, RoundingMode.HALF_EVEN).stripTrailingZeros()
    }
}
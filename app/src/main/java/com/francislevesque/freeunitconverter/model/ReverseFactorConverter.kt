package com.francislevesque.freeunitconverter.model

import java.math.BigDecimal
import java.math.RoundingMode

class ReverseFactorConverter(private val inverseFactor: BigDecimal) : FactorConverter(inverseFactor) {
    override fun toBase(input: BigDecimal, precision: Int): BigDecimal {
        return input.divide(inverseFactor, 20, RoundingMode.HALF_EVEN)
    }

    override fun fromBase(input: BigDecimal, precision: Int): BigDecimal {
        val result = input.multiply(inverseFactor)
        return result.setScale(precision, RoundingMode.HALF_EVEN).stripTrailingZeros()
    }
}
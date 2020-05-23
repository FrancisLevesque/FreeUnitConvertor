package com.francislevesque.freeunitconverter.model

import java.math.BigDecimal
import java.math.RoundingMode

class InvertedFactorConverter(private val inverseFactor: BigDecimal) : FactorConverter(inverseFactor) {
    override fun toBase(input: BigDecimal, precision: Int): BigDecimal {
        return inverseFactor.divide(input, 20, RoundingMode.HALF_EVEN)
    }

    override fun fromBase(input: BigDecimal, precision: Int): BigDecimal {
        return inverseFactor.divide(input, precision, RoundingMode.HALF_EVEN).stripTrailingZeros()
    }
}
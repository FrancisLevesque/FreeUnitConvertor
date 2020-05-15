package com.francislevesque.freeunitconverter.model

import java.math.BigDecimal
import java.math.RoundingMode

open class FactorConverter(private val factor: BigDecimal) {
    open fun toBase(input: BigDecimal, precision: Int): BigDecimal {
        return input.multiply(factor)
    }

    open fun fromBase(input: BigDecimal, precision: Int): BigDecimal {
        return input.divide(factor, precision, RoundingMode.HALF_EVEN).stripTrailingZeros()
    }
}
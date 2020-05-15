package com.francislevesque.freeunitconverter.model

import java.math.BigDecimal
import java.math.RoundingMode

class StepConverter(private val factor: BigDecimal) : FactorConverter(factor) {
    override fun toBase(input: BigDecimal, precision: Int): BigDecimal {
        return input.subtract(factor)
    }

    override fun fromBase(input: BigDecimal, precision: Int): BigDecimal {
        val result = input.add(factor)
        return result.setScale(precision, RoundingMode.HALF_EVEN).stripTrailingZeros()
    }
}
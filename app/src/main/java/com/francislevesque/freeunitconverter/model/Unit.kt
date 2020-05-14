package com.francislevesque.freeunitconverter.model

import java.math.BigDecimal
import java.math.RoundingMode

class Unit(val name: String, private val symbol: String, val type: String, private val factor: BigDecimal) {
    override fun toString(): String {
        return "$name ($symbol)"
    }

    private fun convertFromBase(input: BigDecimal, precision: Int): BigDecimal {
        return input.divide(factor, precision, RoundingMode.HALF_EVEN).stripTrailingZeros()
    }

    fun convert(input: BigDecimal, target: Unit, precision: Int): BigDecimal {
        val result = input.multiply(factor)
        return target.convertFromBase(result, precision)
    }
}
package com.francislevesque.freeunitconverter.model

import java.math.BigDecimal

class Unit(val name: String, private val symbol: String, val tags: ArrayList<String>, private val converter: FactorConverter) {
    override fun toString(): String {
        return "$name ($symbol)"
    }

    private fun convertFromBase(input: BigDecimal, precision: Int): BigDecimal {
        return converter.fromBase(input, precision)
    }

    fun convert(input: BigDecimal, target: Unit, precision: Int): BigDecimal {
        val result = converter.toBase(input, precision)
        return target.convertFromBase(result, precision)
    }
}
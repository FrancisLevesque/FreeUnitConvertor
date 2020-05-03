package com.francislevesque.freeunitconverter.model

class Unit(val name: String, private val symbol: String, val type: String, val factor: Float) {
    override fun toString(): String {
        return "$name ($symbol)"
    }

    private fun convertFromBase(input: Float): Float {
        return input / factor
    }

    fun convert(input: Float, target: Unit): Float {
        return target.convertFromBase(input * factor)
    }
}
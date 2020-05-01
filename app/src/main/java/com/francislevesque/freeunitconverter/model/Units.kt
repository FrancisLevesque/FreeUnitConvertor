package com.francislevesque.freeunitconverter.model

object Units {
    private val categories = listOf(
//        "amount of a substance",
        "distance",
        "electric current",
        "luminous intensity",
        "mass",
        "temperature",
        "time"
    )

    private val units = listOf(
//        Unit("mole", "mol", "amount of a substance"),
        Unit("centimeter", "m", "distance"),
        Unit("kilometer", "m", "distance"),
        Unit("meter", "m", "distance"),
        Unit("ampere", "A", "electric current"),
        Unit("candela", "cd", "luminous intensity"),
        Unit("kilogram", "kg", "mass"),
        Unit("kelvin", "K", "temperature"),
        Unit("second", "s", "time")
    )

    fun categories(): List<String> {
        return categories
    }

    fun units(category: String): List<Unit> {
        return units.filter { unit ->
            unit.type == category
        }
    }
}
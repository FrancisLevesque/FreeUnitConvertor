package com.francislevesque.freeunitconverter.model

class Category(var units: ArrayList<com.francislevesque.freeunitconverter.model.Unit>, val tag: String, val default: String) {
    init {
        units = units.filter { it.tags.contains(tag) } as ArrayList<Unit>
    }

    fun getUnit(name: String): com.francislevesque.freeunitconverter.model.Unit {
        return units.first { it.name.toLowerCase() == name.toLowerCase() }
    }

    fun getDefaultUnit(): com.francislevesque.freeunitconverter.model.Unit {
        return units.first { it.name.toLowerCase() == default.toLowerCase() }
    }

    fun defaultIndex(): Int {
        return units.indexOf(getUnit(default))
    }
}
package com.francislevesque.freeunitconverter.model

import java.util.*
import kotlin.collections.ArrayList

class Category(var units: ArrayList<Unit>, val tag: String, private val default: String) {
    init {
        units = units.filter { it.tags.contains(tag) } as ArrayList<Unit>
    }

    fun getUnit(name: String): Unit {
        return units.first { it.name.toLowerCase(Locale.getDefault()) == name.toLowerCase(Locale.getDefault()) }
    }

    fun getDefaultUnit(): Unit {
        return units.first { it.name.toLowerCase(Locale.getDefault()) == default.toLowerCase(Locale.getDefault()) }
    }

    fun getIndex(unit: Unit): Int {
        return units.indexOf(unit)
    }

    fun getDefaultIndex(): Int {
        return units.indexOf(getUnit(default))
    }

    fun contains(unit: Unit): Boolean {
        return units.contains(unit)
    }

    override fun toString(): String {
        return tag
    }
}
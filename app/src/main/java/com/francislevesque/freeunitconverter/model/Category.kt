package com.francislevesque.freeunitconverter.model

import kotlin.Unit

class Category(val units: ArrayList<com.francislevesque.freeunitconverter.model.Unit>, val default: com.francislevesque.freeunitconverter.model.Unit) {
    fun defaultIndex(): Int {
        return units.indexOf(default)
    }
}
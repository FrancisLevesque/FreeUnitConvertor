package com.francislevesque.freeunitconverter.model

class Unit(val name: String, val symbol: String, val type: String) {
    override fun toString(): String {
        return name
    }
}
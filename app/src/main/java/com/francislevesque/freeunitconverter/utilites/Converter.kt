package com.francislevesque.freeunitconverter.utilites

import android.util.Log

object Converter {

    fun convert(input: Float, inputType: String, outputType: String): Float {
        if (input.isNaN()) {
            return 0.0f
        }
        return when(inputType) {
            "meter" -> when(outputType) {
                "foot" -> input / 0.3048f
                else -> {
                    Log.e("UNSUPPORTED CONVERSION:", "$inputType to $outputType is not supported!")
                    return input
                }
            }
            "foot" -> when(outputType) {
                "meter" -> input * 0.3048f
                else -> {
                    Log.e("UNSUPPORTED CONVERSION:", "$inputType to $outputType is not supported!")
                    return input
                }
            }
            else -> {
                Log.e("UNSUPPORTED CONVERSION:", "$inputType to $outputType is not supported!")
                return input
            }
        }
    }
}
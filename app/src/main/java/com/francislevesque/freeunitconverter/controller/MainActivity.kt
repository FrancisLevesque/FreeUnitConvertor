package com.francislevesque.freeunitconverter.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.francislevesque.freeunitconverter.R
import com.francislevesque.freeunitconverter.model.Unit
import com.francislevesque.freeunitconverter.model.Units
import com.francislevesque.freeunitconverter.utilites.Converter
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigDecimal

class MainActivity : AppCompatActivity() {
    private var currentCategory = Units.categories.first()
    private lateinit var currentUnitList: List<Unit>
    private lateinit var currentUnit : String
    private lateinit var convertUnit : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupTextChangedListener()

        currentUnitList = Units.unitsFor(currentCategory)

        val categoryAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, Units.categories)
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categorySpinner.adapter = categoryAdapter

        val unitToConvertAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, ArrayList<Unit>())
        unitToConvertAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        unitToConvertSpinner.adapter = unitToConvertAdapter

        val unitToConvertIntoAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, ArrayList<Unit>())
        unitToConvertIntoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        unitToConvertIntoSpinner.adapter = unitToConvertIntoAdapter

        categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?){}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                currentCategory = Units.categories[position]
                currentUnitList = Units.unitsFor(currentCategory)
                currentUnit = currentUnitList.first().name
                convertUnit = currentUnitList.first().name

                unitToConvertAdapter.clear()
                unitToConvertAdapter.addAll(currentUnitList)
                unitToConvertAdapter.notifyDataSetChanged()

                unitToConvertIntoAdapter.clear()
                unitToConvertIntoAdapter.addAll(currentUnitList)
                unitToConvertIntoAdapter.notifyDataSetChanged()
            }
        }

        unitToConvertSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?){}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                currentUnit = currentUnitList[position].name
                if (valueToConvert.text.isNotEmpty()) {
                    convert(valueToConvert.text.toString().toFloat())
                }
            }
        }

        unitToConvertIntoSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?){}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                convertUnit = currentUnitList[position].name
                if (valueToConvert.text.isNotEmpty()) {
                    convert(valueToConvert.text.toString().toFloat())
                }
            }
        }
    }

    private fun convert(value: Float) {
        val convertedValue = Converter.convert(value, currentUnit, convertUnit)
        valueToConvertInto.setText(convertedValue.toString())
    }

    private fun setupTextChangedListener() {
        valueToConvert.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(value: CharSequence?, start: Int, before: Int, count: Int) {
                if (value.isNullOrBlank()) {
                    valueToConvertInto.setText("")
                } else {
                    convert(value.toString().toFloat())
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }
}

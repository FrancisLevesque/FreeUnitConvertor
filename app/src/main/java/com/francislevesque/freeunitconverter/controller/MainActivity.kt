package com.francislevesque.freeunitconverter.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.francislevesque.freeunitconverter.R
import com.francislevesque.freeunitconverter.model.Unit
import com.francislevesque.freeunitconverter.model.Units
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigDecimal

class MainActivity : AppCompatActivity() {
    private var currentCategory = Units.categories.first()
    private lateinit var currentUnitList: List<Unit>
    private lateinit var currentUnit : Unit
    private lateinit var convertUnit : Unit

    // TODO:
    //   - Create switcher icon and functionality
    //   - Implement category class for default category unit
    //   - Implement more complicated conversions (for temp)
    //   - Add copy button?
    //   - Create app icon
    //   - Switch unit types from String to a list of tags
    //   - Create categories based off of unit tags
    //   - Add tests
    //   - Add layout for tablets

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
                currentUnit = currentUnitList.first()
                convertUnit = currentUnitList.first()

                unitToConvertAdapter.clear()
                unitToConvertAdapter.addAll(currentUnitList)
                unitToConvertAdapter.notifyDataSetChanged()
                unitToConvertSpinner.setSelection(0)

                unitToConvertIntoAdapter.clear()
                unitToConvertIntoAdapter.addAll(currentUnitList)
                unitToConvertIntoAdapter.notifyDataSetChanged()
                unitToConvertIntoSpinner.setSelection(0)
            }
        }

        unitToConvertSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?){}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                currentUnit = currentUnitList[position]
                if (valueToConvert.text.isNotEmpty()) {
                    convert(valueToConvert.text.toString().toBigDecimal())
                }
            }
        }

        unitToConvertIntoSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?){}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                convertUnit = currentUnitList[position]
                if (valueToConvert.text.isNotEmpty()) {
                    convert(valueToConvert.text.toString().toBigDecimal())
                }
            }
        }
    }

    private fun convert(value: BigDecimal) {
        try {
            valueToConvertInto.text = currentUnit.convert(value, convertUnit).toString()
        } catch (error: java.lang.ArithmeticException) {
            Log.e("ERROR", "Couldn't convert $value ${currentUnit.name} to ${convertUnit.name}")
            valueToConvertInto.text = "NaN"
        }
    }

    private fun setupTextChangedListener() {
        valueToConvert.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(value: CharSequence?, start: Int, before: Int, count: Int) {
                if (value.isNullOrBlank() || value.toString() == ".") {
                    valueToConvertInto.text = ""
                } else {
                    convert(value.toString().toBigDecimal())
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }
}

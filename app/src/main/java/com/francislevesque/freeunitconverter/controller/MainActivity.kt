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
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var currentCategory = Units.categories.first()
    private lateinit var currentUnitList: List<Unit>
    private lateinit var currentUnit : Unit
    private lateinit var convertUnit : Unit

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

                unitToConvertIntoAdapter.clear()
                unitToConvertIntoAdapter.addAll(currentUnitList)
                unitToConvertIntoAdapter.notifyDataSetChanged()
            }
        }

        unitToConvertSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?){}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                currentUnit = currentUnitList[position]
                if (valueToConvert.text.isNotEmpty()) {
                    convert(valueToConvert.text.toString().toFloat())
                }
            }
        }

        unitToConvertIntoSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?){}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                convertUnit = currentUnitList[position]
                if (valueToConvert.text.isNotEmpty()) {
                    convert(valueToConvert.text.toString().toFloat())
                }
            }
        }
    }

    private fun convert(value: Float) {
        valueToConvertInto.setText(currentUnit.convert(value, convertUnit).toString())
    }

    private fun setupTextChangedListener() {
        valueToConvert.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(value: CharSequence?, start: Int, before: Int, count: Int) {
                // TODO: Handle when unit is entered starting with decimal point
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

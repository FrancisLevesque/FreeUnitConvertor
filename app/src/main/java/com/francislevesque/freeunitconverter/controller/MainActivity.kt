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
    private lateinit var fromUnit : Unit
    private lateinit var toUnit : Unit

    // TODO:
    //   - Implement category class for default category unit
    //   - Implement more complicated conversions (for temp)
    //   - Add copy button?
    //   - Create app icon
    //   - Switch unit types from String to a list of tags
    //   - Create categories based off of unit tags
    //   - Add tests

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupTextChangedListener()

        currentUnitList = Units.unitsFor(currentCategory)

        val categoryAdapter = ArrayAdapter(this, R.layout.unit_spinner_item, Units.categories)
        categoryAdapter.setDropDownViewResource(R.layout.unit_spinner_item)
        categorySpinner.adapter = categoryAdapter

        val fromAdapter = ArrayAdapter(this, R.layout.unit_spinner_item, ArrayList<Unit>())
        fromAdapter.setDropDownViewResource(R.layout.unit_spinner_item)
        fromSpinner.adapter = fromAdapter

        val toAdapter = ArrayAdapter(this, R.layout.unit_spinner_item, ArrayList<Unit>())
        toAdapter.setDropDownViewResource(R.layout.unit_spinner_item)
        toSpinner.adapter = toAdapter

        categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?){}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                currentCategory = Units.categories[position]
                currentUnitList = Units.unitsFor(currentCategory)
                fromUnit = currentUnitList.first()
                toUnit = currentUnitList.first()

                fromAdapter.clear()
                fromAdapter.addAll(currentUnitList)
                fromAdapter.notifyDataSetChanged()
                fromSpinner.setSelection(0)

                toAdapter.clear()
                toAdapter.addAll(currentUnitList)
                toAdapter.notifyDataSetChanged()
                toSpinner.setSelection(0)
            }
        }

        fromSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?){}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                fromUnit = currentUnitList[position]
                if (fromValue.text.isNotEmpty()) {
                    convert(fromValue.text.toString().toBigDecimal())
                }
            }
        }

        toSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?){}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                toUnit = currentUnitList[position]
                if (fromValue.text.isNotEmpty()) {
                    convert(fromValue.text.toString().toBigDecimal())
                }
            }
        }

        flipButton.setOnClickListener {
            val holderValue = toValue.text
            toValue.text = fromValue.text
            fromValue.setText(holderValue)

            val holderItemPosition = toSpinner.selectedItemPosition
            toSpinner.setSelection(fromSpinner.selectedItemPosition)
            fromSpinner.setSelection(holderItemPosition)

            val holderUnit = toUnit
            toUnit = fromUnit
            fromUnit = holderUnit
        }
    }

    private fun convert(value: BigDecimal) {
        try {
            toValue.text = fromUnit.convert(value, toUnit).toString()
        } catch (error: java.lang.ArithmeticException) {
            Log.e("ERROR", "Couldn't convert $value ${fromUnit.name} to ${toUnit.name}")
            toValue.text = "NaN"
        }
    }

    private fun setupTextChangedListener() {
        fromValue.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(value: CharSequence?, start: Int, before: Int, count: Int) {
                if (value.isNullOrBlank() || value.toString() == ".") {
                    toValue.text = ""
                } else {
                    convert(value.toString().toBigDecimal())
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }
}

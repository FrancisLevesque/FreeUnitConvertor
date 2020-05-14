package com.francislevesque.freeunitconverter.controller

import android.R.attr.label
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.francislevesque.freeunitconverter.R
import com.francislevesque.freeunitconverter.model.Category
import com.francislevesque.freeunitconverter.model.Unit
import com.francislevesque.freeunitconverter.model.Units
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigDecimal


class MainActivity : AppCompatActivity() {
    private var selectedCategoryName = Units.defaultCategory()
    private lateinit var currentCategory: Category
    private lateinit var fromUnit : Unit
    private lateinit var toUnit : Unit

    // TODO:
    //   - Implement more complicated conversions (for temp)
    //   - Switch unit types from String to a list of tags
    //   - Create categories based off of unit tags
    //   - Add tests

    private val precisionValues = arrayListOf<Int>(2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupTextChangedListener()

        currentCategory = Units.setCategory(selectedCategoryName)

        val categoryAdapter = ArrayAdapter(this, R.layout.unit_spinner_item, Units.categories)
        categorySpinner.adapter = categoryAdapter

        val fromAdapter = ArrayAdapter(this, R.layout.unit_spinner_item, ArrayList<Unit>())
        fromSpinner.adapter = fromAdapter

        val toAdapter = ArrayAdapter(this, R.layout.unit_spinner_item, ArrayList<Unit>())
        toSpinner.adapter = toAdapter

        val precisionAdapter = ArrayAdapter(this, R.layout.unit_spinner_item, precisionValues)
        precisionSpinner.adapter = precisionAdapter
        precisionSpinner.setSelection(3)

        categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?){}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                selectedCategoryName = Units.categories[position]
                currentCategory = Units.setCategory(selectedCategoryName)
                fromUnit = currentCategory.default
                toUnit = currentCategory.default

                fromAdapter.clear()
                fromAdapter.addAll(currentCategory.units)
                fromAdapter.notifyDataSetChanged()
                fromSpinner.setSelection(currentCategory.defaultIndex())

                toAdapter.clear()
                toAdapter.addAll(currentCategory.units)
                toAdapter.notifyDataSetChanged()
                toSpinner.setSelection(currentCategory.defaultIndex())
            }
        }

        fromSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?){}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                fromUnit = currentCategory.units[position]
                if (fromValue.text.isNotEmpty()) {
                    convert(fromValue.text.toString().toBigDecimal())
                }
            }
        }

        toSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?){}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                toUnit = currentCategory.units[position]
                if (fromValue.text.isNotEmpty()) {
                    convert(fromValue.text.toString().toBigDecimal())
                }
            }
        }

        precisionSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?){}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                updateToUnit(fromValue.text)
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
            toValue.text = fromUnit.convert(value, toUnit, precisionSpinner.selectedItem as Int).toString()
        } catch (error: java.lang.ArithmeticException) {
            Log.e("ERROR", "Couldn't convert $value ${fromUnit.name} to ${toUnit.name}")
            toValue.text = "NaN"
        }
    }

    private fun updateToUnit(value: CharSequence?) {
        if (value.isNullOrBlank() || value.toString() == ".") {
            toValue.text = ""
        } else {
            convert(value.toString().toBigDecimal())
        }
    }

    private fun setupTextChangedListener() {
        fromValue.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(value: CharSequence?, start: Int, before: Int, count: Int) {
                updateToUnit(value)
            }
            override fun afterTextChanged(s: Editable?) {
                if (fromValue.text.isNullOrBlank()){
                    clearButton.visibility = View.INVISIBLE
                } else {
                    clearButton.visibility = View.VISIBLE
                }
                if (toValue.text.isNullOrBlank()){
                    copyButton.visibility = View.INVISIBLE
                } else {
                    copyButton.visibility = View.VISIBLE
                }
            }
        })
    }

    fun clearButtonClicked(view: View) {
        fromValue.setText("")
    }

    fun copyButtonClicked(view: View) {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip: ClipData = ClipData.newPlainText("Converted Value", toValue.text)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(this, "Value copied to clipboard!", Toast.LENGTH_SHORT).show()
    }
}

package com.francislevesque.freeunitconverter.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.francislevesque.freeunitconverter.R
import com.francislevesque.freeunitconverter.model.Units
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupTextChangedListener()

        val categoryAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, Units.categories())
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categorySpinner.adapter = categoryAdapter

        val unitToConvertAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, Units.units(Units.categories().first()))
        unitToConvertAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        unitToConvert.adapter = unitToConvertAdapter

        val unitToConvertIntoAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, Units.units(Units.categories().first()))
        unitToConvertIntoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        unitToConvertInto.adapter = unitToConvertIntoAdapter

        categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                unitToConvertAdapter.clear()
                unitToConvertAdapter.addAll(Units.units(Units.categories()[position]))
                unitToConvertAdapter.notifyDataSetChanged()

                unitToConvertIntoAdapter.clear()
                unitToConvertIntoAdapter.addAll(Units.units(Units.categories()[position]))
                unitToConvertIntoAdapter.notifyDataSetChanged()
            }

        }
    }

    private fun setupTextChangedListener() {
        valueToConvert.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                valueToConvertInto.text = s as Editable?
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }
}

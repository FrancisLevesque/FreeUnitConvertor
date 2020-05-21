package com.francislevesque.freeunitconverter.controller

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.francislevesque.freeunitconverter.R
import com.francislevesque.freeunitconverter.model.Category
import com.francislevesque.freeunitconverter.model.Unit
import com.francislevesque.freeunitconverter.model.Units
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigDecimal
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var currentCategory: Category
    private lateinit var fromUnit : Unit
    private lateinit var toUnit : Unit

    // TODO:
    //   - Add more units
    //     - gigabyte/bit...
    //   - Add more tests
    //   - Add climbing grade conversions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupTextChangedListener()

        currentCategory = Units.getCategoryFromName(App.prefs.lastUsedCategory)
        val categoryAdapter = ArrayAdapter(this, R.layout.unit_spinner_item, Units.categories)
        categorySpinner.adapter = categoryAdapter
        val position = Units.categories.indexOf(currentCategory.toString())
        categorySpinner.setSelection(position)

        val fromAdapter = ArrayAdapter(this, R.layout.unit_spinner_item, ArrayList<Unit>())
        fromSpinner.adapter = fromAdapter
        fromUnit = currentCategory.getUnit(App.prefs.fromUnit)
        if (!currentCategory.contains(fromUnit)) {
            setFromUnit(currentCategory.getDefaultUnit())
        }
        fromSpinner.setSelection(currentCategory.getIndex(fromUnit))

        val toAdapter = ArrayAdapter(this, R.layout.unit_spinner_item, ArrayList<Unit>())
        toSpinner.adapter = toAdapter
        toUnit = currentCategory.getUnit(App.prefs.toUnit)
        if (!currentCategory.contains(toUnit)) {
            setToUnit(currentCategory.getDefaultUnit())
        }
        toSpinner.setSelection(currentCategory.getIndex(toUnit))

        val precisionSlider = findViewById<SeekBar>(R.id.precisionSlider)
        precisionSlider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                precisionValue.text = precisionSlider.progress.toString()
                updateToUnit(fromValue.text)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?){}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                if(App.prefs.lastUsedCategory == Units.categories[position]) {
                    fromUnit = currentCategory.getUnit(App.prefs.fromUnit)
                    toUnit = currentCategory.getUnit(App.prefs.toUnit)
                } else {
                    val selection = Units.categories[position]
                    App.prefs.lastUsedCategory = selection
                    currentCategory = Units.getCategoryFromName(selection)
                    setFromUnit(currentCategory.getDefaultUnit())
                    setToUnit(currentCategory.getDefaultUnit())
                }
                fromAdapter.clear()
                fromAdapter.addAll(currentCategory.units)
                fromAdapter.notifyDataSetChanged()
                fromSpinner.setSelection(currentCategory.getIndex(fromUnit))

                toAdapter.clear()
                toAdapter.addAll(currentCategory.units)
                toAdapter.notifyDataSetChanged()
                toSpinner.setSelection(currentCategory.getIndex(toUnit))
            }
        }

        fromSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?){}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                setFromUnit(currentCategory.units[position])
                if (fromValue.text.isNotEmpty()) {
                    convert(fromValue.text.toString().toBigDecimal())
                }
            }
        }

        toSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?){}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                setToUnit(currentCategory.units[position])
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

    private fun setFromUnit(unit: Unit) {
        fromUnit = unit
        App.prefs.fromUnit = fromUnit.name
    }

    private fun setToUnit(unit: Unit) {
        toUnit = unit
        App.prefs.toUnit = toUnit.name
    }

    private fun convert(value: BigDecimal) {
        try {
            toValue.text = fromUnit.convert(value, toUnit, precisionSlider.progress).toPlainString()
        } catch (error: java.lang.ArithmeticException) {
            Log.e("ERROR", "Couldn't convert $value ${fromUnit.name} to ${toUnit.name}")
            toValue.text = "NaN"
        }
    }

    private fun updateToUnit(value: CharSequence?) {
        if (value.isNullOrBlank() || value.toString() == "." || value.toString() == "-") {
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
        Toast.makeText(this, "Value copied to clipboard", Toast.LENGTH_SHORT).show()
    }

    private fun hideKeyboard() {
        currentFocus?.let {
            val inputMethodManager = ContextCompat.getSystemService(this, InputMethodManager::class.java)!!
            inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        val view = currentFocus
        if (view != null) {
            if (event != null && (event.action == MotionEvent.ACTION_UP || event.action == MotionEvent.ACTION_MOVE)
                && view is EditText &&
                !view::class.simpleName?.startsWith("android.webkit.")!!
            ) {
                val coordinates = IntArray(2)
                view.getLocationOnScreen(coordinates)
                val x: Float = event.rawX + view.left - coordinates[0]
                val y: Float = event.rawY + view.top - coordinates[1]

                if (x < view.left || x > view.right || y < view.top || y > view.bottom) {
                    hideKeyboard()
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }

}

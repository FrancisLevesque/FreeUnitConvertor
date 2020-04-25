package com.francislevesque.freeunitconverter.controller

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.francislevesque.freeunitconverter.R
import com.francislevesque.freeunitconverter.model.Unit
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val units = listOf(
        Unit("kilogram", "kg", "mass"),
        Unit("second", "s", "time"),
        Unit("kelvin", "K", "temperature"),
        Unit("ampere", "A", "electric current"),
        Unit("mole", "mol", "amount of a substance"),
        Unit("candela", "cd", "luminous intensity"),
        Unit("meter", "m", "distance")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupTextChangedListener()

        val unitAdapter = UnitAdapter(this, R.layout.unit_list_item, units)
        mainUnitToConvert.setAdapter(unitAdapter)
        mainUnitToConvert.setOnItemClickListener { parent, _, position, _ ->
            val selectedUnit = parent.adapter.getItem(position) as Unit?
            mainUnitToConvert.setText(selectedUnit?.name)
        }
    }

    private fun setupTextChangedListener() {
        mainValueToConvert.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                mainValueToConvertInto.text = s as Editable?
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    inner class UnitAdapter(context: Context, @LayoutRes private val layoutResource: Int, private val allUnits: List<Unit>):
        ArrayAdapter<Unit>(context, layoutResource, allUnits),
        Filterable {
        private var filteredUnits = allUnits

        override fun getCount(): Int {
            return filteredUnits.size
        }

        override fun getItem(position: Int): Unit? {
            return filteredUnits[position]
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view: TextView = convertView as TextView? ?: LayoutInflater.from(context).inflate(layoutResource, parent, false) as TextView
            view.text = "${filteredUnits[position].name}"
            return view
        }

        override fun getFilter(): Filter {
            return object : Filter() {
                override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                    if (results != null) {
                        filteredUnits = results.values as List<Unit>
                    }
                    notifyDataSetChanged()
                }

                override fun performFiltering(constraint: CharSequence?): FilterResults {
                    val query = constraint?.toString()?.toLowerCase()

                    val results = FilterResults()
                    results.values = if (query == null || query.isEmpty())
                        allUnits
                    else
                        allUnits.filter { unit ->
                            unit.name.toLowerCase().contains(query) ||
                                    unit.symbol.toLowerCase().contains(query) ||
                                    unit.type.toLowerCase().contains(query)
                        }
                    return results
                }
            }
        }
    }
}

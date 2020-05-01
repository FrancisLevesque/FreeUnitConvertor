package com.francislevesque.freeunitconverter.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.francislevesque.freeunitconverter.model.Unit

class AutoCompleteUnitAdapter(context: Context, @LayoutRes private val layoutResource: Int, private val allUnits: List<Unit>):
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
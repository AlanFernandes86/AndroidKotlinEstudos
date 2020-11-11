package com.example.analize.ui.symbolsearch.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.analize.R
import com.example.analize.service.model.alphavantage.SymbolModel
import com.example.analize.ui.symbolsearch.listener.SymbolSearchListener

class SymbolSearchViewHolder(itemView: View, private val listener: SymbolSearchListener) : RecyclerView.ViewHolder(itemView) {

    fun bind(symbol: SymbolModel){
        val companyName = itemView.findViewById<TextView>(R.id.text_fragment_symbol_search_company_name)
        val companySymbol = itemView.findViewById<TextView>(R.id.text_fragment_symbol_search_company_symbol)
        val companyRegion = itemView.findViewById<TextView>(R.id.text_fragment_symbol_search_company_region)

        companyName.text = symbol.name
        companySymbol.text = symbol.symbol
        companyRegion.text = symbol.region

        itemView.setOnClickListener(){
                listener.onClick(symbol)
        }

    }

}
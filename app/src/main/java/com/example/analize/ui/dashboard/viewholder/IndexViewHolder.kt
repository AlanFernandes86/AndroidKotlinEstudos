package com.example.analize.ui.dashboard.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.analize.R
import com.example.analize.service.model.alphavantage.GlobalQuoteModel
import com.example.analize.service.model.alphavantage.SymbolModel
import com.example.analize.ui.dashboard.listener.IndexListener


class IndexViewHolder(itemView: View, val listener: IndexListener) : RecyclerView.ViewHolder(itemView) {

    private val name: TextView = itemView.findViewById<TextView>(R.id.text_row_dashboard_company_name)
    private val symbol: TextView = itemView.findViewById<TextView>(R.id.text_row_dashboard_symbol)
    //private val region = itemView.findViewById<TextView>()
    private val data = itemView.findViewById<TextView>(R.id.text_row_dashboard_data)
    private val price = itemView.findViewById<TextView>(R.id.text_row_dashboard_price)
    private val change = itemView.findViewById<TextView>(R.id.text_row_dashboard_change)
    private val changePercent = itemView.findViewById<TextView>(R.id.text_row_dashboard_change_percent)


    fun bind(symbolModel: SymbolModel){
        name.text = symbolModel.name
        symbol.text = symbolModel.symbol
       //region.text = symbolModel.region

        itemView.setOnClickListener(){
            listener.onClick(symbolModel)
        }

    }

    fun bind2(globalQuoteModel: GlobalQuoteModel){
        data.text = globalQuoteModel.latestTradingDay
        price.text = globalQuoteModel.price
        change.text = globalQuoteModel.change
        changePercent.text = globalQuoteModel.changePercent
    }






}
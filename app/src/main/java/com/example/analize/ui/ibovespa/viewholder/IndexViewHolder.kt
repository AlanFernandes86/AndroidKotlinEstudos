package com.example.analize.ui.ibovespa.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.analize.R
import com.example.analize.service.constants.ConstantsIbovespa
import com.example.analize.service.model.ibovespa.MostTradedModel
import com.example.analize.service.model.ibovespa.UpDownModel
import com.example.analize.ui.ibovespa.listener.IndexListener


class IndexViewHolder(itemView: View/*, val listener: IndexListener*/) : RecyclerView.ViewHolder(itemView) {

    fun bindMostTraded(stock: MostTradedModel.Stock){
        val symbol = itemView.findViewById<TextView>(R.id.index_text_symbol)
        val company = itemView.findViewById<TextView>(R.id.index_text_company)
        val price = itemView.findViewById<TextView>(R.id.index_text_price)
        val percent = itemView.findViewById<TextView>(R.id.index_text_percent)
        val index = itemView.findViewById<TextView>(R.id.index_text_var_index)

        symbol.text = stock.company[ConstantsIbovespa.COMPANY.SYMBOL]
        company.text = stock.company[ConstantsIbovespa.COMPANY.DESCRIPTION]
        price.text = stock.priceValue.toString()
        percent.text = ""
        index.text = ""
    }

    fun bindUpDown(stock: UpDownModel.Stock){
        val symbol = itemView.findViewById<TextView>(R.id.index_text_symbol)
        val company = itemView.findViewById<TextView>(R.id.index_text_company)
        val priceText = itemView.findViewById<TextView>(R.id.index_text_price)
        val percentText = itemView.findViewById<TextView>(R.id.index_text_percent)
        val varIndexText = itemView.findViewById<TextView>(R.id.index_text_var_index)

        val price = stock.stockFloat[ConstantsIbovespa.STOCK_FLOAT.PRICE]
        val percent= stock.stockFloat[ConstantsIbovespa.STOCK_FLOAT.PRICE_DIF]

        symbol.text = stock.symbol
        company.text = stock.description
        priceText.text = "%.2f".format(price)
        percentText.text = "${"%.2f".format(percent)} %"
        varIndexText.text = percentToValue(price!!, percent!!)
    }

    private fun percentToValue(price: Float, percent: Float): String {
        var perc = percent
        var result: Float = 0.2f
        if (percent < 0) {
            perc = percent * (-1)
            result = ((price/(1-(perc/100)))-price) * (-1)
        }
        else{
            result = (price/(1-(perc/100)))-price
        }

        return "%.2f".format(result)
    }



}
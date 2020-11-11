package com.example.analize.ui.symbolsearch.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.analize.R
import com.example.analize.service.model.alphavantage.SymbolModel
import com.example.analize.ui.symbolsearch.listener.SymbolSearchListener
import com.example.analize.ui.symbolsearch.viewholder.SymbolSearchViewHolder

class SymbolSearchAdapter : RecyclerView.Adapter<SymbolSearchViewHolder>() {

    private var mSymbolList: List<SymbolModel> = arrayListOf()
    private lateinit var mListener: SymbolSearchListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SymbolSearchViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.row_symbol_search_recycler, parent, false)
        return SymbolSearchViewHolder(item, mListener)
    }

    override fun getItemCount(): Int {
        return mSymbolList.count()
    }

    override fun onBindViewHolder(holder: SymbolSearchViewHolder, position: Int) {
        holder.bind(mSymbolList[position])
    }

    fun updateSymbolSearch(symbols: List<SymbolModel>){
        mSymbolList = symbols
        notifyDataSetChanged()
    }

    fun attachListener(listener: SymbolSearchListener){
        mListener = listener
    }

}
package com.example.analize.ui.dashboard.adapter


import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import com.example.analize.R
import com.example.analize.service.model.alphavantage.GlobalQuoteModel
import com.example.analize.service.model.alphavantage.SymbolModel
import com.example.analize.ui.dashboard.listener.IndexListener
import com.example.analize.ui.dashboard.viewholder.IndexViewHolder


class IndexAdapter(): RecyclerView.Adapter<IndexViewHolder>() {

    private var mListSymbol: List<SymbolModel> = arrayListOf()
    private lateinit var mListener: IndexListener
    private lateinit var mListGlobalQuote: List<GlobalQuoteModel>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IndexViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.row_dashboard_recycler, parent, false)
        return IndexViewHolder(item, mListener)
    }

    override fun getItemCount(): Int {
        return mListSymbol.count()
    }

    override fun onBindViewHolder(holder: IndexViewHolder, position: Int) {
        holder.bind(mListSymbol[position])

        if(::mListGlobalQuote.isInitialized && mListGlobalQuote.isNotEmpty()) {
            for (quote in mListGlobalQuote){
                if(mListSymbol[position].symbol == quote.symbol){
                    holder.bind2(quote)
                }
            }
        }
    }

    fun updateSymbol(list: List<SymbolModel>){
        mListSymbol = list
        notifyDataSetChanged()
    }

    fun updateGlobalQuote(list: List<GlobalQuoteModel>){
        mListGlobalQuote = list
        notifyDataSetChanged()
    }

    fun attachListener(listener: IndexListener) {
        mListener = listener
    }




}
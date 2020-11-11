package com.example.analize.ui.ibovespa.adapter

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import com.example.analize.R
import com.example.analize.service.model.ibovespa.MostTradedModel
import com.example.analize.service.model.ibovespa.UpDownModel
import com.example.analize.ui.ibovespa.viewholder.IndexViewHolder


class IndexAdapter(): RecyclerView.Adapter<IndexViewHolder>() {

    private var mList: List<Any> = arrayListOf()
    //private var mListener: IndexListener = IndexListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IndexViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.row_ibovespa_recycler, parent, false)
        return IndexViewHolder(item)
    }

    override fun getItemCount(): Int {
        return mList.count()
    }

    override fun onBindViewHolder(holder: IndexViewHolder, position: Int) {
        when (mList[position]) {
            is MostTradedModel.Stock -> {
                holder.bindMostTraded(mList[position] as MostTradedModel.Stock)
            }
            else -> {
                holder.bindUpDown(mList[position] as UpDownModel.Stock)
            }
        }
    }

    fun update(list: List<Any>){
        mList = when (list[0]) {
            is MostTradedModel.Stock -> list as List<MostTradedModel.Stock>
            else -> list as List<UpDownModel.Stock>
        }
        notifyDataSetChanged()
    }

    /*fun attachListener(listener: IndexListener) {
        mListener = listener
    }*/




}
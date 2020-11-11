package com.example.analize.ui.dashboard.listener

import com.example.analize.service.model.alphavantage.GlobalQuoteModel
import com.example.analize.service.model.alphavantage.SymbolModel

interface IndexListener {

    fun onClick(symbolModel: SymbolModel)

}
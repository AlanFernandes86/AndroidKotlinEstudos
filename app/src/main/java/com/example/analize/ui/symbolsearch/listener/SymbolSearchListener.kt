package com.example.analize.ui.symbolsearch.listener

import com.example.analize.service.model.alphavantage.SymbolModel

interface SymbolSearchListener {
    fun onClick(symbolModel: SymbolModel)
}
package com.example.analize.service.repository.local

import android.content.Context
import com.example.analize.service.model.alphavantage.GlobalQuoteModel
import com.example.analize.service.model.alphavantage.SymbolModel
import com.example.analize.service.model.alphavantage.SymbolQuoteModel

class AlphaVantageRepository(context: Context) {

    private val mDatabase = Database.getDatabase(context).alphaVantageDAO()

    inner class Symbol {
        fun insert(symbolModel: SymbolModel) = mDatabase.insertSymbol(symbolModel)
        fun get(symbol: String): SymbolModel = mDatabase.getSymbol(symbol)
        fun update(symbolModel: SymbolModel): Int = mDatabase.updateSymbol(symbolModel)
        fun delete(symbolModel: SymbolModel) = mDatabase.deleteSymbol(symbolModel)
        fun list() : List<SymbolModel> = mDatabase.listSymbol()
    }

    inner class GlobalQuote {
        fun insert(globalQuoteModel: GlobalQuoteModel) = mDatabase.insertGlobalQuote(globalQuoteModel)
        fun get(symbol: String): SymbolModel = mDatabase.getSymbol(symbol)
        fun update(globalQuoteModel: GlobalQuoteModel): Int = mDatabase.updateGlobalQuote(globalQuoteModel)
        fun delete(globalQuoteModel: GlobalQuoteModel) = mDatabase.deleteGlobalQuote(globalQuoteModel)
        fun list() : List<GlobalQuoteModel> = mDatabase.listGlobalQuote()
    }






}
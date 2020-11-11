package com.example.analize.service.repository.local

import androidx.room.*
import com.example.analize.service.model.alphavantage.GlobalQuoteModel
import com.example.analize.service.model.alphavantage.SymbolModel
import com.example.analize.service.model.alphavantage.SymbolQuoteModel

@Dao
interface AlphaVantageDAO {

    @Insert
    fun insertSymbol(symbolModel: SymbolModel)

    @Update
    fun updateSymbol(symbolModel: SymbolModel) : Int

    @Delete
    fun deleteSymbol(symbolModel: SymbolModel)

    @Query("SELECT * FROM Symbol WHERE symbol = :symbol")
    fun getSymbol(symbol: String) : SymbolModel

    @Query("SELECT * FROM Symbol")
    fun listSymbol() : List<SymbolModel>

    @Insert
    fun insertGlobalQuote(globalQuoteModel: GlobalQuoteModel)

    @Update
    fun updateGlobalQuote(globalQuoteModel: GlobalQuoteModel) : Int

    @Delete
    fun deleteGlobalQuote(globalQuoteModel: GlobalQuoteModel)

    @Query("SELECT * FROM GlobalQuote WHERE global_symbol = :symbol")
    fun getGlobalQuote(symbol: String) : GlobalQuoteModel

    @Query("SELECT * FROM GlobalQuote")
    fun listGlobalQuote(): List<GlobalQuoteModel>

    @Query("SELECT * FROM Symbol INNER JOIN GlobalQuote ON GlobalQuote.global_symbol = Symbol.symbol")
    fun listSymbolQuote() : List<SymbolQuoteModel>;

}
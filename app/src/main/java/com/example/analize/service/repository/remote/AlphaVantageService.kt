package com.example.analize.service.repository.remote

import com.example.analize.service.model.alphavantage.DailyModel
import com.example.analize.service.model.alphavantage.GlobalQuoteModel
import com.example.analize.service.model.alphavantage.SymbolModel
import retrofit2.Call
import retrofit2.http.HTTP
import retrofit2.http.Query


interface AlphaVantageService {

    @HTTP(method = "GET", path = "query", hasBody = false)
    fun searchEndpoint(
        @Query(value = "function", encoded = false) function: String,
        @Query(value = "keywords", encoded = false) keywords: String,
        @Query(value = "apikey", encoded = true) apikey: String
    ): Call<Map<String, List<SymbolModel>>>

    @HTTP(method = "GET", path = "query", hasBody = false)
    fun timeSeriesDaily(
        @Query(value = "function", encoded = false) function: String,
        @Query(value = "symbol", encoded = false) symbol: String,
        @Query(value = "interval", encoded = false) interval: String = "",
        @Query(value = "outputsize", encoded = false) outputsize: String = "",
        @Query(value = "apikey", encoded = true) apikey: String
        ): Call<DailyModel>

    @HTTP(method = "GET", path = "query", hasBody = false)
    fun globalQuote(
        @Query(value = "function", encoded = false) function: String = "GLOBAL_QUOTE",
        @Query(value = "symbol", encoded = false) symbol: String,
        @Query(value = "apikey", encoded = true) apikey: String
    ): Call<Map<String, GlobalQuoteModel>>

}
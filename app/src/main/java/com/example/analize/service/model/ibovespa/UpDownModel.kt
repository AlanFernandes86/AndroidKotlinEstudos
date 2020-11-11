package com.example.analize.service.model.ibovespa

import com.google.gson.annotations.SerializedName

class UpDownModel {

    @SerializedName("SctyHghstIncrLst")
    lateinit var upStocks:  List<Stock>

    @SerializedName("SctyHghstDrpLst")
    lateinit var downStocks: List<Stock>


    inner class Stock {

        @SerializedName("SctyQtn")
        lateinit var stockFloat: Map<String, Float>

        @SerializedName("symb")
        lateinit var symbol: String

        @SerializedName("desc")
        lateinit var description: String

    }

}
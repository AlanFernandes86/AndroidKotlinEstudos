package com.example.analize.service.model.ibovespa

import com.google.gson.annotations.SerializedName

class MostTradedModel {

    @SerializedName("Volume")
    lateinit var volume: List<Stock>

    @SerializedName("BizSts")
    lateinit var status: Map<String, String>

    inner class Stock {

        @SerializedName("scty")
        lateinit var company: Map<String, String>

        @SerializedName("grossAmt")
        var grossAmt: Float = 0.0f

        @SerializedName("pricVal")
        var priceValue: Float = 0.0f

    }




}
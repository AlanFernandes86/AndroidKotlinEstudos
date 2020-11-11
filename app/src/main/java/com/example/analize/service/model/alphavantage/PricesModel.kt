package com.example.analize.service.model.alphavantage

import com.google.gson.annotations.SerializedName

class PricesModel {

    @SerializedName("1. open")
    var open: Float = 0.0f

    @SerializedName("2. high")
    var high: Float = 0.0f

    @SerializedName("3. low")
    var low: Float = 0.0f

    @SerializedName("4. close")
    var close: Float = 0.0f

    @SerializedName("5. volume")
    var volume: Float = 0.0f

}
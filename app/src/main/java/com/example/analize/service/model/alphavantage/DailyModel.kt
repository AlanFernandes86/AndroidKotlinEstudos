package com.example.analize.service.model.alphavantage

import com.google.gson.annotations.SerializedName

class DailyModel {
    @SerializedName("Meta Data")
    lateinit var metaData: MetaDataModel

    @SerializedName("Time Series (Daily)")
    lateinit var timeSeriesDaily: Map<String, PricesModel>
}
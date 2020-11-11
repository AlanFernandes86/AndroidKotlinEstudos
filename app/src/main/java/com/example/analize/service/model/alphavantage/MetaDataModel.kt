package com.example.analize.service.model.alphavantage

import com.google.gson.annotations.SerializedName

class MetaDataModel {

    @SerializedName("1. Information")
    lateinit var information: String

    @SerializedName("2. Symbol")
    lateinit var symbol: String

    @SerializedName("3. Last Refreshed")
    lateinit var LastRefreshed: String

    @SerializedName("4. Output Size")
    lateinit var outputSize: String

    @SerializedName("5. Time Zone")
    lateinit var timeZone: String

}
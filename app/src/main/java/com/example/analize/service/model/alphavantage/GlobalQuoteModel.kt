package com.example.analize.service.model.alphavantage

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import com.google.gson.annotations.SerializedName

@Entity(tableName = "GlobalQuote",
        foreignKeys = [
                ForeignKey(entity = SymbolModel::class,
                parentColumns = ["symbol"],
                childColumns = ["global_symbol"],
                onDelete = CASCADE)],
        indices = [Index("global_symbol")]
        )
class GlobalQuoteModel {

    @PrimaryKey
    @ColumnInfo(name = "global_symbol")
    @SerializedName("01. symbol")
    lateinit var symbol: String

    @ColumnInfo(name = "open")
    @SerializedName("02. open")
    lateinit var open: String

    @ColumnInfo(name = "high")
    @SerializedName("03. high")
    lateinit var high: String

    @ColumnInfo(name = "low")
    @SerializedName("04. low")
    lateinit var low: String

    @ColumnInfo(name = "price")
    @SerializedName("05. price")
    lateinit var price: String

    @ColumnInfo(name = "volume")
    @SerializedName("06. volume")
    lateinit var volume: String

    @ColumnInfo(name = "latestTradingDay")
    @SerializedName("07. latest trading day")
    lateinit var latestTradingDay: String

    @ColumnInfo(name = "previousClose")
    @SerializedName("08. previous close")
    lateinit var previousClose: String

    @ColumnInfo(name = "change")
    @SerializedName("09. change")
    lateinit var change: String

    @ColumnInfo(name = "changePercent")
    @SerializedName("10. change percent")
    lateinit var changePercent: String

}


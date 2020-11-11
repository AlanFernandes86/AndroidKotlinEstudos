package com.example.analize.service.model.alphavantage

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Symbol")
class SymbolModel {

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "symbol")
    @SerializedName("1. symbol")
    lateinit var symbol: String

    @ColumnInfo(name = "name")
    @SerializedName("2. name")
    lateinit var name: String

    @ColumnInfo(name = "type")
    @SerializedName("3. type")
    lateinit var type: String

    @ColumnInfo(name = "region")
    @SerializedName("4. region")
    lateinit var region: String

    @ColumnInfo(name = "marketOpen")
    @SerializedName("5. marketOpen")
    lateinit var marketOpen: String

    @ColumnInfo(name = "marketClose")
    @SerializedName("6. marketClose")
    lateinit var marketClose: String

    @ColumnInfo(name = "timezone")
    @SerializedName("7. timezone")
    lateinit var timezone: String

    @ColumnInfo(name = "currency")
    @SerializedName("8. currency")
    lateinit var currency: String

    @ColumnInfo(name = "matchScore")
    @SerializedName("9. matchScore")
    var matchScore: Float = 0.0f
}
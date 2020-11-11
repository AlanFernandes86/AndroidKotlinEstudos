package com.example.analize.service.model.alphavantage

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SymbolQuote")
class SymbolQuoteModel {

    @Embedded
    lateinit var symbolModel: SymbolModel

    @Embedded
    lateinit var globalQuoteModel: GlobalQuoteModel

}

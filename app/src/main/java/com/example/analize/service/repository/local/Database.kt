package com.example.analize.service.repository.local

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.analize.service.model.alphavantage.GlobalQuoteModel
import com.example.analize.service.model.alphavantage.SymbolModel

@androidx.room.Database(entities = [SymbolModel::class, GlobalQuoteModel::class], version = 1)
abstract class Database: RoomDatabase() {

    abstract fun alphaVantageDAO(): AlphaVantageDAO

    companion object {
        private lateinit var INSTANCE: Database
        fun getDatabase(context: Context): Database {
            if (!::INSTANCE.isInitialized){
                synchronized(Database::class){
                    INSTANCE = Room.databaseBuilder(context, Database::class.java, "analizeDB")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

    }


}
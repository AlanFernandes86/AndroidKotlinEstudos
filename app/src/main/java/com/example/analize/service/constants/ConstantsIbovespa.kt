package com.example.analize.service.constants

class ConstantsIbovespa private constructor(){

    companion object {
        const val BASE_URL = "http://cotacao.b3.com.br/mds/api/v1/"
    }

    object ENDPOINT {
        const val MOST_TRADED = "InstrumentTradeVolume/vista"
        const val UP_DOWN = "InstrumentPriceFluctuation/ibov"
    }

    object COMPANY {
        const val SYMBOL = "symb"
        const val DESCRIPTION = "desc"
    }

    object STATUS {
        const val CODIGO = "cd"
        const val DESCRIPTION = "desc"
    }

    object STOCK_FLOAT {
        const val PRICE = "curPrc"
        const val PRICE_DIF = "prcFlcn"
    }







}
package com.example.analize.service.constants

class ConstantsAlphaVantage private constructor() {
    object TIME_SERIES {
        const val INTRADAY = "TIME_SERIES_INTRADAY"
        const val DAILY = "TIME_SERIES_DAILY"
        const val WEEKLY = "TIME_SERIES_WEEKLY"
        const val WEEKLY_ADJUSTED = "TIME_SERIES_WEEKLY_ADJUSTED"
        const val MONTHLY = "TIME_SERIES_MONTHLY"
        const val MONTHLY_ADJUSTED = "TIME_SERIES_MONTHLY_ADJUSTED"
    }

    object SEARCH_ENDPOINT {
        const val SYMBOL_SEARCH = "SYMBOL_SEARCH"
    }

    companion object {
        const val BASE_URL = "https://www.alphavantage.co/"
        const val API_KEY = "apikey"
    }

    object SYMBOL_SEARCH_MESSAGE {
        const val START = "start"
        const val NOK_APIKEY = "nok_api"
        const val OK_APIKEY = "ok_api"
        const val INVALID_API = "invalid_api"
        const val NOK_COMPANY = "nok_company"
        const val OK_COMPANY = "ok_company"
    }

    object MAP_KEYS {
        const val SYMBOL_SEARCH = "bestMatches"
        const val QUOTE_ENDPOINT = "Global Quote"
    }

    object OUTPUT_SIZE {
        const val COMPACT = "compact"
        const val FULL = "full"
    }


}
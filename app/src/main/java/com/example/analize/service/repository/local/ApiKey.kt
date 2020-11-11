package com.example.analize.service.repository.local

import java.util.*
import kotlin.random.Random


class ApiKey private constructor() {

    companion object {

        private lateinit var lastGetDateTime: Calendar

        private var index = 0
        private var flag = 0

        private val KEYS = arrayOf(
                "A7JQS8CLZGZB2AJ3",
                "WJ0V4NU8KIJJ2D3S",
                "6EPH1QR7D4WBAVPN",
                "GQFBDRRWIJ98E1DT",
                "AN8IB7K04JKX9ZNC",
                "D93IMTU1CJH9H4PZ",
                "HSFK02RN3V5CPSV3",
                "8PE6L92JRFGN8JDJ",
                "SJ2V46SSPO5T8JSQ",
                "ZG9PFVZIB8ZWJQ7F"
        )

        private fun toSeconds(calendar: Calendar):Int {
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)
            val second = calendar.get(Calendar.SECOND)
            return ((hour*3600) + (minute*60) + second)
        }

        fun getApiKey() : String {
            val now = Calendar.getInstance()
            if(!::lastGetDateTime.isInitialized) {
                lastGetDateTime = now
                 return KEYS[index]
            }
            else if ((toSeconds(now) - toSeconds(lastGetDateTime)) <= 60 ) {
                flag++
                if (flag == 5) {
                    if (index == KEYS.size - 1) index = 0 else index++
                    flag = 0
                }
                lastGetDateTime = now
                return KEYS[index]
            }
            else if ((toSeconds(now) - toSeconds(lastGetDateTime)) > 60 ) {
                flag = 0
                index = Random.nextInt(0,KEYS.size - 1)
                lastGetDateTime = now
                return KEYS[index]
            }
            return "error"
        }
    }

}
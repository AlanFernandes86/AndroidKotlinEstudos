package com.example.analize.ui.bottomnavigation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.analize.service.constants.ConstantsAlphaVantage
import com.example.analize.service.model.alphavantage.DailyModel
import com.example.analize.service.repository.local.SecurityPreferences
import com.example.analize.service.repository.remote.AlphaVantageService
import com.example.analize.service.repository.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BottomNavigationViewModel(application: Application) : AndroidViewModel(application) {

    private val context = application.applicationContext
    private val remote = RetrofitClient.createService(serviceClass = AlphaVantageService::class.java)
    private var apikey: String

    init {
        apikey = SecurityPreferences(context).get(ConstantsAlphaVantage.API_KEY)
    }

    fun loadSymbol() {
        val call: Call<DailyModel> = remote.timeSeriesDaily(
            function = ConstantsAlphaVantage.TIME_SERIES.DAILY,
            symbol = "IBM",
            apikey = "A7JQS8CLZGZB2AJ3"
        )
        val response = call.enqueue(object : Callback<DailyModel> {
            override fun onFailure(call: Call<DailyModel>, t: Throwable) {
                println(t.cause)
            }

            override fun onResponse(call: Call<DailyModel>, res: Response<DailyModel>) {
                val r = res.body()
            }
        })
    }


}
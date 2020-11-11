package com.example.analize.ui.dashboard

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.analize.service.constants.ConstantsAlphaVantage
import com.example.analize.service.model.alphavantage.GlobalQuoteModel
import com.example.analize.service.model.alphavantage.SymbolModel
import com.example.analize.service.repository.local.AlphaVantageRepository
import com.example.analize.service.repository.local.SecurityPreferences
import com.example.analize.service.repository.remote.AlphaVantageService
import com.example.analize.service.repository.remote.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardViewModel(application: Application): AndroidViewModel(application) {

    private val mAlphaVantageRepository = AlphaVantageRepository(application.applicationContext)
    private val remote = RetrofitClient.createService(serviceClass = AlphaVantageService::class.java)
    private var apikey: String = SecurityPreferences(application.applicationContext).get(ConstantsAlphaVantage.API_KEY)

    private val mListSymbol = MutableLiveData<List<SymbolModel>>()
    val listSymbol: LiveData<List<SymbolModel>> = mListSymbol

    private val mListGlobalQuote = MutableLiveData<List<GlobalQuoteModel>>()
    val listGlobalQuote: LiveData<List<GlobalQuoteModel>> = mListGlobalQuote

    fun loadListSymbol(){
        mListSymbol.value = mAlphaVantageRepository.Symbol().list()
        viewModelScope.launch {
            loadRemoteGlobalQuotes()
        }
    }

    fun loadLocalGlobalQuotes(){
        mListGlobalQuote.value = mAlphaVantageRepository.GlobalQuote().list()
    }

    private suspend fun loadRemoteGlobalQuotes(){
        withContext(Dispatchers.IO) {
            for (item in mListSymbol.value!!) {
                val call: Call<Map<String,GlobalQuoteModel>> = remote.globalQuote(symbol = item.symbol, apikey = apikey)
                call.enqueue(object: Callback<Map<String,GlobalQuoteModel>> {
                    override fun onResponse(call: Call<Map<String,GlobalQuoteModel>>, response: Response<Map<String,GlobalQuoteModel>>) {
                        val res = response.body()!![ConstantsAlphaVantage.MAP_KEYS.QUOTE_ENDPOINT]
                        if (res != null)
                            mAlphaVantageRepository.GlobalQuote().update(res)
                    }
                    override fun onFailure(call: Call<Map<String,GlobalQuoteModel>>, t: Throwable) {
                        println(t.message)
                    }
                })
            }// FIM DO FOR
        }
        withContext(Dispatchers.Main) {
            loadLocalGlobalQuotes()
        }
    }

    fun deleteSymbol(symbolModel: SymbolModel){
        mAlphaVantageRepository.Symbol().delete(symbolModel)
        loadListSymbol()
    }

}
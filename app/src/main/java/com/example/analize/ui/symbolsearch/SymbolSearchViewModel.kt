package com.example.analize.ui.symbolsearch

import android.app.Application
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.analize.service.constants.ConstantsAlphaVantage
import com.example.analize.service.model.alphavantage.GlobalQuoteModel
import com.example.analize.service.model.alphavantage.SymbolModel
import com.example.analize.service.repository.local.AlphaVantageRepository
import com.example.analize.service.repository.local.SecurityPreferences
import com.example.analize.service.repository.remote.AlphaVantageService
import com.example.analize.service.repository.remote.RetrofitClient
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class SymbolSearchViewModel(application: Application) : AndroidViewModel(application) {

    private val mAlphaVantageRepository = AlphaVantageRepository(application.applicationContext)
    private val context = application.applicationContext
    private val remote =
        RetrofitClient.createService(serviceClass = AlphaVantageService::class.java)
    private var apikey: String

    private val mMessageApiKey = MutableLiveData<String>().apply {
        value = ConstantsAlphaVantage.SYMBOL_SEARCH_MESSAGE.NOK_APIKEY
    }
    val messageApiKey: LiveData<String> = mMessageApiKey

    private val mListSymbolSearch = MutableLiveData<List<SymbolModel>>()
    val listSymbol: LiveData<List<SymbolModel>> = mListSymbolSearch

    init {
        apikey = SecurityPreferences(context).get(ConstantsAlphaVantage.API_KEY)
        if (apikey != "") {
            mMessageApiKey.value = ConstantsAlphaVantage.SYMBOL_SEARCH_MESSAGE.START
        }
    }

    fun loadRecyclerFragment(keywords: String) {
        if (keywords != "") {
            val call: Call<Map<String, List<SymbolModel>>> =
                remote.searchEndpoint(
                    function = ConstantsAlphaVantage.SEARCH_ENDPOINT.SYMBOL_SEARCH,
                    keywords = keywords,
                    apikey = apikey
                )
            val response = call.enqueue(object : Callback<Map<String, List<SymbolModel>>> {
                override fun onFailure(call: Call<Map<String, List<SymbolModel>>>, t: Throwable) {
                    println(t.message)
                }

                override fun onResponse(
                    call: Call<Map<String, List<SymbolModel>>>,
                    res: Response<Map<String, List<SymbolModel>>>
                ) {
                    val r = res.body()
                    mListSymbolSearch.value = r?.get(ConstantsAlphaVantage.MAP_KEYS.SYMBOL_SEARCH)
                    if (mListSymbolSearch.value.isNullOrEmpty())
                        mMessageApiKey.value =
                            ConstantsAlphaVantage.SYMBOL_SEARCH_MESSAGE.NOK_COMPANY
                    else
                        mMessageApiKey.value =
                            ConstantsAlphaVantage.SYMBOL_SEARCH_MESSAGE.OK_COMPANY
                }
            })
        }
    }

    fun selectedSymbol(symbol: SymbolModel, activity: FragmentActivity): Boolean {
        return try {
            mAlphaVantageRepository.Symbol().insert(symbol)
            val call: Call<Map<String, GlobalQuoteModel>> = remote.globalQuote(symbol = symbol.symbol, apikey = apikey)
            call.enqueue(object : Callback<Map<String, GlobalQuoteModel>> {
                override fun onFailure(call: Call<Map<String, GlobalQuoteModel>>, t: Throwable) {
                    println("selectedSymbol: " + t.message)
                }
                override fun onResponse(
                    call: Call<Map<String, GlobalQuoteModel>>,
                    res: Response<Map<String, GlobalQuoteModel>>
                ) {
                    val r = res.body()!![ConstantsAlphaVantage.MAP_KEYS.QUOTE_ENDPOINT]
                    if (r != null) {
                        mAlphaVantageRepository.GlobalQuote().insert(r)
                        activity.finish()
                    }
                }
            })
            true
        }catch (e: Exception){
            false
        }

    }

    fun apiKey(apikey: String): Boolean {
        return try {
            SecurityPreferences(context).store(ConstantsAlphaVantage.API_KEY, apikey)
            mMessageApiKey.value = ConstantsAlphaVantage.SYMBOL_SEARCH_MESSAGE.OK_APIKEY
            this.apikey = SecurityPreferences(context).get(ConstantsAlphaVantage.API_KEY)
            true
        } catch (e: Exception) {
            false
        }

    }


}
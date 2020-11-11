package com.example.analize.ui.ibovespa

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.analize.service.constants.ConstantsIbovespa
import com.example.analize.service.model.ibovespa.MostTradedModel
import com.example.analize.service.model.ibovespa.UpDownModel
import com.example.analize.service.repository.remote.IbovespaService
import com.example.analize.service.repository.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IbovespaViewModel(application: Application) : AndroidViewModel(application) {

    private val remote = RetrofitClient.createService(ConstantsIbovespa.BASE_URL, IbovespaService::class.java)

    private val mListMostTraded = MutableLiveData<MostTradedModel>()
    val listMostTraded: LiveData<MostTradedModel> = mListMostTraded

    private val mListUpDown = MutableLiveData<UpDownModel>()
    val listUpDown: LiveData<UpDownModel> = mListUpDown

    fun load(owner: Any) {
        return when (owner) {
            is MostTradedFragment -> {
                mostTraded()
            }
            else -> {
                upDown()
            }
        }
    }

    private fun mostTraded(){
        val call: Call<MostTradedModel> = remote.mostTraded()
        val response = call.enqueue(object : Callback<MostTradedModel> {
            override fun onFailure(call: Call<MostTradedModel>, t: Throwable) {
                println(t.message)
            }

            override fun onResponse(call: Call<MostTradedModel>, response: Response<MostTradedModel>) {
                mListMostTraded.value = response.body()
            }
        })

    }

    private fun upDown(){
        val call: Call<UpDownModel> = remote.upDown()
        val response = call.enqueue(object : Callback<UpDownModel> {
            override fun onFailure(call: Call<UpDownModel>, t: Throwable) {
                println(t.message)
            }

            override fun onResponse(call: Call<UpDownModel>, response: Response<UpDownModel>) {
                mListUpDown.value = response.body()
            }

        })
    }


}
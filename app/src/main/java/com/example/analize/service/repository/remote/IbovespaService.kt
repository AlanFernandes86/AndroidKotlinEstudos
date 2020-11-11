package com.example.analize.service.repository.remote

import com.example.analize.service.constants.ConstantsIbovespa
import com.example.analize.service.model.ibovespa.MostTradedModel
import com.example.analize.service.model.ibovespa.UpDownModel
import retrofit2.Call
import retrofit2.http.GET

interface IbovespaService {

    @GET(ConstantsIbovespa.ENDPOINT.UP_DOWN)
    fun upDown(): Call<UpDownModel>

    @GET(ConstantsIbovespa.ENDPOINT.MOST_TRADED)
    fun mostTraded(): Call<MostTradedModel>

}
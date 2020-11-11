package com.example.analize.service.repository.remote

import com.example.analize.service.constants.ConstantsAlphaVantage
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor() {

    companion object {

        private lateinit var retrofit: Retrofit
        private lateinit var mBaseURL: String

        private fun getRetrofitInstance(): Retrofit {

            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val request =
                        chain.request()
                            .newBuilder()
                            .build()
                    return chain.proceed(request)
                }
            })

            if (!Companion::retrofit.isInitialized) {
                retrofit = Retrofit.Builder()
                    .baseUrl(mBaseURL)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            } else {
                if (mBaseURL != retrofit.baseUrl().toString()) {
                    retrofit = Retrofit.Builder()
                        .baseUrl(mBaseURL)
                        .client(httpClient.build())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
            }
            return retrofit
        }

        fun <S> createService(baseURL: String = ConstantsAlphaVantage.BASE_URL, serviceClass: Class<S>): S {
            mBaseURL = baseURL
            return getRetrofitInstance().create(serviceClass)
        }
    }
}
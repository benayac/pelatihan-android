package com.example.pelatihanandroid.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.covid19api.com")
            .build()
    }

    fun getService() = getRetrofit().create(APICovid::class.java)
}
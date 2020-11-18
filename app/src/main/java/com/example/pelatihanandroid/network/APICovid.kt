package com.example.pelatihanandroid.network

import com.example.pelatihanandroid.model.Country
import com.example.pelatihanandroid.model.DataByCountry
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APICovid {

    @GET("countries")
    fun getCountryList(): Call<List<Country>>

    @GET("total/country/{countryInput}")
    fun getDataByCountry(@Path("countryInput") countryInput: String,
                         @Query("from") from: String,
                         @Query("to") to: String): Call<List<DataByCountry>>
}
package com.example.pelatihanandroid.network

import com.example.pelatihanandroid.model.Country
import com.example.pelatihanandroid.model.DataByCountry
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface APICovid {

    @GET("https://api.covid19api.com/countries")
    fun getCountryList(): Call<List<Country>>

    @GET("country/{countryInput}?from=2020-11-12T00:00:00Z&to=2020-11-13T00:00:00Z")
    fun getDataByCountry(@Path("countryInput") countryInput: String): Call<List<DataByCountry>>
}
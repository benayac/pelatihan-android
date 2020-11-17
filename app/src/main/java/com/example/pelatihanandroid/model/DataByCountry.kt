package com.example.pelatihanandroid.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataByCountry(

	@field:SerializedName("DataByCountry")
	val dataByCountry: DataByCountry? = null,

	@field:SerializedName("CityCode")
	val cityCode: String? = null,

	@field:SerializedName("Recovered")
	val recovered: Int? = null,

	@field:SerializedName("Active")
	val active: Int? = null,

	@field:SerializedName("Country")
	val country: String? = null,

	@field:SerializedName("Deaths")
	val deaths: Int? = null,

	@field:SerializedName("Lon")
	val lon: String? = null,

	@field:SerializedName("City")
	val city: String? = null,

	@field:SerializedName("Confirmed")
	val confirmed: Int? = null,

	@field:SerializedName("CountryCode")
	val countryCode: String? = null,

	@field:SerializedName("Province")
	val province: String? = null,

	@field:SerializedName("Lat")
	val lat: String? = null,

	@field:SerializedName("Date")
	val date: String? = null
) : Parcelable

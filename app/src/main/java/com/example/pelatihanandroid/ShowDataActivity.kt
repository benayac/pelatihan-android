package com.example.pelatihanandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.pelatihanandroid.model.Country
import com.example.pelatihanandroid.model.DataByCountry
import com.example.pelatihanandroid.network.RetrofitBuilder
import kotlinx.android.synthetic.main.activity_show_data.*
import kotlinx.android.synthetic.main.country_info_card.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.util.*

class ShowDataActivity : AppCompatActivity() {
    private var countryId = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_data)

        progressBar.visibility = View.VISIBLE
        val data = intent.getStringExtra("EXTRA_DATA")
        countryId = intent.getStringExtra("EXTRA_DATA_COUNTRY_ID") ?: ""
        val sdf = SimpleDateFormat("yyyy-MM-dd")

        val currentDate = sdf.format(Date())
        val yesterdayDate = sdf.format(getDaysAgo(1))

        Log.d("DATELOG", "current date = $currentDate yesterday date = $yesterdayDate")
        RetrofitBuilder().getService().getDataByCountry(data, yesterdayDate, currentDate).enqueue(object: Callback<List<DataByCountry>> {
            override fun onFailure(call: Call<List<DataByCountry>>, t: Throwable) {
                //TERJADI KETIKA API CALL GAGAL
                progressBar.visibility = View.GONE
            }

            override fun onResponse(
                call: Call<List<DataByCountry>>,
                response: Response<List<DataByCountry>>
            ) {
                if(response.body()?.size ?: 0 > 0) {
                    setViewData(response.body()?.get(0))
                } else {
                    allDataConstraintLayout.visibility = View.GONE
                    noDataTextView.visibility = View.VISIBLE
                }
                progressBar.visibility = View.GONE
            }

        })
    }

    private fun setViewData(country: DataByCountry?) {
        Glide.with(this)
            .load("https://www.countryflags.io/${countryId}/flat/64.png")
            .into(countryFlagImage)

        val parser = SimpleDateFormat("yyyy-MM-dd")
        val formatter = SimpleDateFormat("dd-MM-yyy")

        countryNameTextView.text = country?.country

        dateTextView.visibility = View.VISIBLE
        dateTextView.text = formatter.format(parser.parse(country?.date))

        data1TextView.text = country?.confirmed.toString()
        data2TextView.text = country?.deaths.toString()
        data3TextView.text = country?.recovered.toString()
        data4TextView.text = country?.active.toString()
    }

    private fun getDaysAgo(daysAgo: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -daysAgo)
        return calendar.time
    }

}
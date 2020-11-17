package com.example.pelatihanandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pelatihanandroid.model.DataByCountry
import com.example.pelatihanandroid.network.RetrofitBuilder
import kotlinx.android.synthetic.main.activity_show_data.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShowDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_data)

        val data = intent.getStringExtra("EXTRA_DATA")

        RetrofitBuilder().getService().getDataByCountry(data).enqueue(object: Callback<List<DataByCountry>> {
            override fun onFailure(call: Call<List<DataByCountry>>, t: Throwable) {
                //TERJADI KETIKA API CALL GAGAL
            }

            override fun onResponse(
                call: Call<List<DataByCountry>>,
                response: Response<List<DataByCountry>>
            ) {
                val result = response.body()?.get(0)?.deaths
                dataTextView.text = "Death = " + result
            }

        })
    }

}
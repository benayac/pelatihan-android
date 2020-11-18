package com.example.pelatihanandroid

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pelatihanandroid.adapters.CountryListAdapter
import com.example.pelatihanandroid.model.Country
import com.example.pelatihanandroid.network.RetrofitBuilder
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var searchView: SearchView
    private lateinit var mAdapter: CountryListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Covid By Country"
        setupRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchManager: SearchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu?.findItem(R.id.action_search)?.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.maxWidth = Integer.MAX_VALUE

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                mAdapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                mAdapter.filter.filter(newText)
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_search) {
            return true
        } else {
            return super.onOptionsItemSelected(item)
        }
    }

    private fun setupRecyclerView() {
        val gridLayoutManager = GridLayoutManager(this, 3)
        countryRecyclerView.layoutManager = gridLayoutManager

        RetrofitBuilder().getService().getCountryList().enqueue(object: Callback<List<Country>>{
            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                Toast.makeText(applicationContext, "Failed to load countries list", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
                mAdapter = CountryListAdapter(response.body() as ArrayList<Country>?) {
                    countryClickedListener(it)
                }
                countryRecyclerView.adapter = mAdapter
            }
        })
    }

    private fun countryClickedListener(country: Country) {
        val intent = Intent(this, ShowDataActivity::class.java)
        intent.putExtra("EXTRA_DATA", country.slug)
        intent.putExtra("EXTRA_DATA_COUNTRY_ID", country.iSO2)
        startActivity(intent)
    }
}
package com.example.pelatihanandroid.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pelatihanandroid.R
import com.example.pelatihanandroid.model.Country
import kotlinx.android.synthetic.main.country_info_card.view.*

class CountryListAdapter(private val countries: ArrayList<Country>?,
                         private val itemClickListener: (Country) -> Unit): RecyclerView.Adapter<CountryListAdapter.ViewHolder>(), Filterable {

    private var countryFiltered = countries

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bind(data: Country?, clickListener: (Country) -> Unit) {
            itemView.countryNameTextView.text = data?.country
            Glide.with(itemView)
                .load("https://www.countryflags.io/${data?.iSO2}/flat/64.png")
                .into(itemView.countryFlagImage)

            itemView.setOnClickListener {
                if(data != null) {
                    clickListener(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.country_info_card, parent, false))
    }

    override fun getItemCount(): Int {
        return countryFiltered?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(countryFiltered?.get(position), itemClickListener)
    }

    override fun getFilter(): Filter {
        return object: Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint.toString()
                if(charString.isEmpty()) {
                    if (countries != null) {
                        countryFiltered = countries
                    }
                } else {
                    val filteredList: ArrayList<Country> = arrayListOf()
                    if (countries != null) {
                        for(row in countries) {
                            if(row.country?.toLowerCase()?.contains(charString.toLowerCase()) == true
                                || row.slug?.toLowerCase()?.contains(charString.toLowerCase()) == true
                                || row.iSO2?.toLowerCase()?.contains(charString.toLowerCase()) == true
                            ) {
                                filteredList.add(row)
                            }
                        }
                    }
                    countryFiltered = filteredList
                }
                val filterResult = FilterResults()
                filterResult.values = countryFiltered
                return filterResult
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                countryFiltered = results?.values as (ArrayList<Country>)
                notifyDataSetChanged()
            }

        }
    }
}
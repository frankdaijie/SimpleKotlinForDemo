package com.frankdaijie.kotlinforpractise

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView

/**
 */

class ForecastListAdapter(val forecastList: ForecastList)
    : RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(forecastList.dailyForecast[position]) {
            holder.textView.text = "$date - $description - $highestTemp/$lowestTemp"
        }
    }

    override fun getItemCount(): Int = forecastList.dailyForecast.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TextView(parent.context))
    }

    class ViewHolder(val textView: TextView)
        : RecyclerView.ViewHolder(textView)
}
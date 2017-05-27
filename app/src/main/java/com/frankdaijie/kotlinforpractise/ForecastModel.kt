package com.frankdaijie.kotlinforpractise

/**
 * Created by frankdj412 on 27/05/2017.
 */

data class ForecastList(val city: String, val country: String, val dailyForecast: List<Forecast>) {
    override fun toString(): String {
        return city
    }
}

data class Forecast(val date: String, val description: String, val highestTemp: Int, val lowestTemp: Int)

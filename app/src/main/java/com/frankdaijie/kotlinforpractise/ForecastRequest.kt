package com.frankdaijie.kotlinforpractise

import android.util.Log
import com.google.gson.Gson
import java.net.URL

/**
 */

public class ForecastRequest(val cityName: String) {

    companion object {
        public val CITY_BEIJING = "beijing"

        private val APP_ID = "7e91a6e068ab87c7e515aee353d2e9d7"
        private val URL = "http://api.openweathermap.org/data/2.5/forecast/daily?mode=json&units=metric&cnt=7"
        private val COMPLETE_URL = "$URL&APPID=$APP_ID&q="
    }

    fun execute(): ForecastResult? {
        val result = URL(COMPLETE_URL + cityName).readText()
        return Gson().fromJson(result, ForecastResult::class.java)
    }
}

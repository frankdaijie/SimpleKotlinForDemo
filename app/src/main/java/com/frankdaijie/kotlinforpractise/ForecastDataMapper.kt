package com.frankdaijie.kotlinforpractise

import android.util.Log
import java.text.DateFormat
import java.util.*

/**
 */
class ForecastDataMapper {

    public fun getForecastListFromResult(forecastResult: ForecastResult): ForecastList {

        return ForecastList(forecastResult.city.name,
                forecastResult.city.country,
                convertToList(forecastResult.list))
    }

    private fun convertToList(list: List<ForecastItem>): List<Forecast> {

        var newList = list.map { (dt, temp, _, _, weather) ->
            Forecast(getDate(dt),
                    weather[0].description,
                    temp.max.toInt(),
                    temp.min.toInt())
        }

        Log.d("ForecastDataMapper", newList.toString())
        return newList
    }

    private fun getDate(date: Long): String {
        val dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return dateFormat.format(date * 1000)
    }

}
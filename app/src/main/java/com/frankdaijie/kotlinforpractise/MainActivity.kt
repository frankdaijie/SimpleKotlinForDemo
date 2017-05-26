package com.frankdaijie.kotlinforpractise

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        forecast_list.layoutManager = LinearLayoutManager(this)

        start_button.setOnClickListener {
            toast("Start to get weather data")

            async {
                val result = ForecastRequest(ForecastRequest.CITY_BEIJING).execute()

                if (result != null) {
                    uiThread {
                        val listResult: ForecastList = ForecastDataMapper().getForecastListFromResult(result)
                        toast("Done")
                        forecast_list.adapter = ForecastListAdapter(listResult)
                    }
                } else {
                    uiThread { toast("failed to get data") }
                }
            }
        }
    }
}

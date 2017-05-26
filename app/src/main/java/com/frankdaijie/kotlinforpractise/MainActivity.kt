package com.frankdaijie.kotlinforpractise

import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        forecast_list.layoutManager = LinearLayoutManager(this)

        custom_textview.someCustomMethod()

        button_sync.setOnClickListener {
            toast("Start to get weather data")
            doAsync {
                var dialog: ProgressDialog? = null

                uiThread {
                    dialog = indeterminateProgressDialog("Fetching the data…")
                }

                val result = ForecastRequest(ForecastRequest.CITY_BEIJING).execute()

                uiThread {
                    dialog!!.hide()
                    if (result != null) {
                        val listResult: ForecastList = ForecastDataMapper().getForecastListFromResult(result)
                        toast("Done")
                        forecast_list.adapter = ForecastListAdapter(listResult)
                    } else {
                        toast("failed to get data")
                    }
                }
            }
        }

        button_show_alert.setOnClickListener {
            showSampleAlert()
        }

        button_show_selector.setOnClickListener {
            showSampleSelector()
        }

        button_show_custom_alert.setOnClickListener {
            createAndShowCustomLayoutAlert()
        }
    }

    private fun showSampleSelector() {
        val countries = listOf("Russia", "USA", "Japan", "Australia")
        selector("Where are you from?", countries) { _, i ->
            toast("So you're living in ${countries[i]}, right?")
        }

    }

    private fun showSampleAlert() {
        alert("Order", "Do you want to order this item?") {
            positiveButton("Yes") { toast("yes") }
            negativeButton("No") { toast("no") }
        }.show()
    }

    private fun createAndShowCustomLayoutAlert() {
        alert {
            customView {
                verticalLayout {
                    val familyName = editText {
                        hint = "Family name"
                    }
                    val firstName = editText {
                        hint = "First name"
                    }
                    positiveButton("Register") { register(familyName.text, firstName.text) }
                }
            }
        }.show()
    }

    private fun register(familyName: Editable, firstname: Editable) {
        toast("First name: $firstname, Family name: $familyName")
    }
}

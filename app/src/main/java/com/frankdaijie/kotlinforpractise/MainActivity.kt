package com.frankdaijie.kotlinforpractise

import android.app.Activity
import android.app.ProgressDialog
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*

class MainActivity : Activity() {

    // Lazy delegate
    val buttonShowSelector: Button by lazy {
        find<Button>(R.id.button_show_selector)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        forecast_list.layoutManager = LinearLayoutManager(this)

        button_sync.setOnClickListener {
            toast("Start to get weather data")
            val dialog: ProgressDialog = indeterminateProgressDialog("Fetching the data…")

            doAsync {
                val result = ForecastRequest(ForecastRequest.CITY_BEIJING).execute()

                uiThread {
                    dialog.hide()

                    when (result) {
                        null -> {
                            toast("failed to get data")
                        }
                        else -> {
                            val listResult: ForecastList = ForecastDataMapper().getForecastListFromResult(result)
                            toast("Done")
                            forecast_list.adapter = ForecastListAdapter(listResult)

                        }
                    }
                }
            }
        }

        val buttonShowAlert = find<Button>(R.id.button_show_alert)
        buttonShowAlert.setOnClickListener {
            showSampleAlert()
        }

        buttonShowSelector.setOnClickListener {
            showSampleSelector()
        }

        button_show_custom_alert.setOnClickListener {
            createAndShowCustomLayoutAlert()
        }

        button_call_custom_method.setOnClickListener {
            it.background = getDrawable(R.color.background_material_dark)
            custom_textview.someCustomMethod()

            it.postDelayed({
                longToast("Change Color after 2 secs")
                it.background = getDrawable(R.color.background_floating_material_light)
            }, 2000)
        }

        button_goto.setOnClickListener {
            CustomLayoutActivity.newInstance(this, 5)
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
            positiveButton("Yes") {
                toast("yes")
            }
            negativeButton("No") {
                toast("no")
            }
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

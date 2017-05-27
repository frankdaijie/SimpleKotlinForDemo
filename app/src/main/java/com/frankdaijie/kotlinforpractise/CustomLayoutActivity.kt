package com.frankdaijie.kotlinforpractise

import android.app.Activity
import android.content.Context
import android.os.Bundle
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 */
class CustomLayoutActivity : Activity() {

    companion object {
        val ID_KEY: String = "id"

        fun newInstance(context: Context, id: Int) = with(context) {
            startActivity(intentFor<CustomLayoutActivity>(CustomLayoutActivity.ID_KEY to id).singleTop())
        }

        fun newInstanceEquivalent(context: Context, id: Int) {
            with(context) {
                startActivity(intentFor<CustomLayoutActivity>(CustomLayoutActivity.ID_KEY to id).singleTop())
            }
        }

        fun newInstanceWithoutWITHKeyWord(context: Context, id: Int) {
            context.startActivity(context.intentFor<CustomLayoutActivity>(CustomLayoutActivity.ID_KEY to id).singleTop())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val range: Int = intent.getIntExtra(ID_KEY, 100)

        verticalLayout {
            padding = dip(30)
            editText {
                hint = "Name"
                textSize = 24f
            }
            editText {
                hint = "Password"
                textSize = 24f
            }
            textView {

                text = when(range) {
                    in 1..3 -> {
                        "Less than 3"
                    }
                    100 -> {
                        "Default value"
                    }
                    else -> {
                        "Range is $range"
                    }
                }
            }

            button("Login") {
                textSize = 26f
            }
        }

//        MyActivityUI().setContentView(this)
    }
}


class MyActivityUI : AnkoComponent<CustomLayoutActivity> {
    override fun createView(ui: AnkoContext<CustomLayoutActivity>) = with(ui) {
        verticalLayout {
            val name = editText()
            button("Say Hello") {
                onClick { ctx.toast("Hello, ${name.text}!") }
            }
        }
    }
}
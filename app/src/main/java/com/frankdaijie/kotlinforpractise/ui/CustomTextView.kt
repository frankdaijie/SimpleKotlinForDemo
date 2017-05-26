package com.frankdaijie.kotlinforpractise.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import org.jetbrains.anko.toast

/**
 */
class CustomTextView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : TextView(context, attrs, defStyleAttr) {

    fun someCustomMethod() {
        with(context) {
            toast("Custom method is called")
        }
    }
}
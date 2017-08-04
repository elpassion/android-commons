package com.elpassion.android.view

import android.view.View

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

var View.isVisible: Boolean
    set(value) {
        if (value) {
            show()
        } else {
            hide()
        }
    }
    get() {
        return visibility == View.VISIBLE
    }

fun View.disable() {
    isEnabled = false
}

fun View.enable(){
    isEnabled = true
}
package com.elpassion.android.commons.context

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Context.color(colorId: Int) = ContextCompat.getColor(this, colorId)

fun Fragment.snackbar(
        parentView: View, text: Int
) = Snackbar.make(parentView, string(text), Snackbar.LENGTH_LONG)

fun Context.string(textId: Int) = getString(textId)
fun Fragment.string(textId: Int) = getString(textId)

fun Fragment.hideKeyboard() {
    val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(activity?.currentFocus?.windowToken, 0)
}

fun Activity.hideBottomBar() {
    val decorView = window?.decorView
    val options = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_FULLSCREEN
            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
    decorView?.systemUiVisibility = options
}

fun Activity.revertBottomBar() {
    val decorView = window?.decorView
    val options = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
    decorView?.systemUiVisibility = options
}

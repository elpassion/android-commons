package com.elpassion.android.commons.espresso

import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.v7.widget.Toolbar
import org.hamcrest.CoreMatchers.*

fun onId(@IdRes viewId: Int): ViewInteraction = onView(withId(viewId))

fun onText(@StringRes textId: Int): ViewInteraction = onView(withText(textId))

fun onText(text: String): ViewInteraction = onView(withText(text))

fun onToolbarBackArrow(): ViewInteraction {
    val arrowDescription = InstrumentationRegistry.getContext().getString(R.string.abc_action_bar_up_description)
    return onView(allOf(withParent(withClassName(`is`(Toolbar::class.java.name))), withContentDescription(containsString(arrowDescription))))
}
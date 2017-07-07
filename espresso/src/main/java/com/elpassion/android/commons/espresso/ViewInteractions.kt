package com.elpassion.android.commons.espresso

import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.matcher.ViewMatchers.*

fun onId(@IdRes viewId: Int): ViewInteraction = onView(withId(viewId))

fun onText(@StringRes textId: Int): ViewInteraction = onView(withText(textId))

fun onText(text: String): ViewInteraction = onView(withText(text))

fun onToolbarBackArrow(): ViewInteraction {
    InstrumentationRegistry.getTargetContext().run {
        return onView(withContentDescription(resources.getIdentifier("abc_action_bar_up_description", "string", packageName)))
    }
}
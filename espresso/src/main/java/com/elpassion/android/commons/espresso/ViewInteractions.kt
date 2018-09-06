package com.elpassion.android.commons.espresso

import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.test.InstrumentationRegistry
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers.*

fun onId(@IdRes viewId: Int): ViewInteraction = onView(withId(viewId))

fun onText(@StringRes textId: Int): ViewInteraction = onView(withText(textId))

fun onText(text: String): ViewInteraction = onView(withText(text))

fun onToolbarBackArrow(): ViewInteraction {
    InstrumentationRegistry.getTargetContext().run {
        return onView(withContentDescription(resources.getIdentifier("abc_action_bar_up_description", "string", packageName)))
    }
}
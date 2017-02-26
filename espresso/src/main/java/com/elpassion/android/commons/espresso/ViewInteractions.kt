package com.elpassion.android.commons.espresso

import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText

fun onId(@IdRes viewId: Int): ViewInteraction = onView(withId(viewId))

fun onText(@StringRes textId: Int): ViewInteraction = onView(withText(textId))

fun onText(text: String): ViewInteraction = onView(withText(text))
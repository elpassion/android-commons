package com.elpassion.android.commons.espresso

import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.view.View
import org.hamcrest.Matcher
import org.hamcrest.Matchers

fun onId(@IdRes viewId: Int): ViewInteraction = onView(withId(viewId))

fun onText(@StringRes textId: Int): ViewInteraction = onView(withText(textId))

fun onText(text: String): ViewInteraction = onView(withText(text))

fun onToolbarBackArrow(): ViewInteraction = Espresso.onView(ViewMatchers.withContentDescription(R.string.abc_action_bar_up_description))

fun Matcher<View>.onChildWithId(@IdRes childId: Int): ViewInteraction = Espresso.onView(Matchers.allOf(this, ViewMatchers.withId(childId)))
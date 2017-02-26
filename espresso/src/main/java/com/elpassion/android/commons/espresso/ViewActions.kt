package com.elpassion.android.commons.espresso

import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions

fun ViewInteraction.click(): ViewInteraction = perform(ViewActions.click())

fun ViewInteraction.typeText(text: String): ViewInteraction = perform(ViewActions.typeText(text))

fun ViewInteraction.replaceText(text: String): ViewInteraction = perform(ViewActions.replaceText(text))

fun ViewInteraction.pressImeActionButton(): ViewInteraction = perform(ViewActions.pressImeActionButton())

fun ViewInteraction.swipeLeft(): ViewInteraction = perform(ViewActions.swipeLeft())

fun ViewInteraction.swipeRight(): ViewInteraction = perform(ViewActions.swipeRight())

fun ViewInteraction.swipeUp(): ViewInteraction = perform(ViewActions.swipeUp())

fun ViewInteraction.swipeDown(): ViewInteraction = perform(ViewActions.swipeDown())

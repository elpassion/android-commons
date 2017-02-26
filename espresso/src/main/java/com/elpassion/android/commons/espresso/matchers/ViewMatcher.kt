package com.elpassion.android.commons.espresso.matchers

import android.view.View
import org.hamcrest.Description
import org.hamcrest.Matcher

inline fun <reified T : View> createViewMatcher(
        crossinline matchesSafelyImpl: (view: T) -> Boolean,
        crossinline describeToImpl: (description: Description) -> Unit): Matcher<View> {
    return createObjectMatcher<View, T>(matchesSafelyImpl, describeToImpl)
}

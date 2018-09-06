package com.elpassion.android.commons.espresso.matchers

import android.view.View
import androidx.annotation.IdRes
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers

inline fun <reified T : View> createViewMatcher(
        crossinline matchesSafelyImpl: (view: T) -> Boolean,
        crossinline describeToImpl: (description: Description) -> Unit
): Matcher<View> {
    return createObjectMatcher<View, T>(matchesSafelyImpl, describeToImpl)
}

fun Matcher<View>.withParentId(@IdRes parentId: Int): Matcher<View> = Matchers.allOf(this, ViewMatchers.withParent(ViewMatchers.withId(parentId)))
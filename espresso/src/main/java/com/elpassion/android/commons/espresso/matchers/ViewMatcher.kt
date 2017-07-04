package com.elpassion.android.commons.espresso.matchers

import android.support.annotation.IdRes
import android.support.test.espresso.matcher.ViewMatchers
import android.view.View
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
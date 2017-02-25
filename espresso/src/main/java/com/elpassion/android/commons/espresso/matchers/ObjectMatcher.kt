package com.elpassion.android.commons.espresso.matchers

import android.support.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

inline fun <reified Base : Any, reified T : Base> createObjectMatcher(
        crossinline matchesSafelyImpl: (item: T) -> Boolean,
        crossinline describeToImpl: (description: Description) -> Unit): Matcher<Base> {

    return object : BoundedMatcher<Base, T>(T::class.java) {

        override fun matchesSafely(item: T) = matchesSafelyImpl(item)

        override fun describeTo(description: Description) = describeToImpl(description)
    }
}

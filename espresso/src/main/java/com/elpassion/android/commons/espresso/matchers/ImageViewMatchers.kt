package com.elpassion.android.commons.espresso.matchers

import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.ImageView
import org.hamcrest.Matcher

fun withImage(@DrawableRes imageId: Int): Matcher<View> = createViewMatcher(
        matchesSafelyImpl = { view: ImageView -> view.hasImageWithId(imageId) },
        describeToImpl = { it.appendText("has src with id: $imageId") }
)

private fun ImageView.hasImageWithId(@DrawableRes imageId: Int): Boolean {
    val drawable = drawable ?: return false
    val actualState = drawable.constantState
    val expectedState = ContextCompat.getDrawable(context, imageId)?.constantState
    return actualState == expectedState
}

fun withAnyImage(): Matcher<View> = createViewMatcher(
        matchesSafelyImpl = { view: ImageView -> view.drawable != null },
        describeToImpl = { it.appendText("has src set") }
)

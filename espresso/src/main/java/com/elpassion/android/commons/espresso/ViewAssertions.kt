package com.elpassion.android.commons.espresso

import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.espresso.matcher.ViewMatchers.Visibility.GONE
import com.elpassion.android.commons.espresso.matchers.EditTextErrorMatcher
import com.elpassion.android.commons.espresso.matchers.TextInputEditTextHintMatcher
import com.elpassion.android.commons.espresso.matchers.withImage
import com.elpassion.android.commons.espresso.matchers.withAnyImage
import com.elpassion.android.commons.espresso.recycler.containerHasChildCount
import org.hamcrest.Matcher
import org.hamcrest.core.IsNot.not

fun ViewInteraction.isDisplayed(): ViewInteraction = check(matches(ViewMatchers.isDisplayed()))

fun ViewInteraction.isNotDisplayed(): ViewInteraction = check(matches(not(ViewMatchers.isDisplayed())))

fun ViewInteraction.isGone(): ViewInteraction = check(matches(withEffectiveVisibility(GONE)))

fun ViewInteraction.doesNotExist(): ViewInteraction = check(ViewAssertions.doesNotExist())

fun ViewInteraction.hasText(@StringRes textId: Int): ViewInteraction = check(matches(withText(textId)))

fun ViewInteraction.hasText(text: String): ViewInteraction = check(matches(withText(text)))

fun ViewInteraction.hasImage(@DrawableRes imageId: Int) = check(matches(withImage(imageId)))

fun ViewInteraction.doesNotHaveImage(@DrawableRes imageId: Int) = check(matches(not(withImage(imageId))))

fun ViewInteraction.hasAnyImage() = check(matches(withAnyImage()))

fun ViewInteraction.doesNotHaveAnyImage() = check(matches(not(withAnyImage())))

fun ViewInteraction.isEnabled(): ViewInteraction = check(matches(ViewMatchers.isEnabled()))

fun ViewInteraction.isDisabled(): ViewInteraction = check(matches(not(ViewMatchers.isEnabled())))

fun ViewInteraction.isChecked(): ViewInteraction = check(matches(ViewMatchers.isChecked()))

fun ViewInteraction.isNotChecked(): ViewInteraction = check(matches(not(ViewMatchers.isChecked())))

fun ViewInteraction.hasTag(matcher: Matcher<Any>) = check(matches(withTagValue(matcher)))

fun ViewInteraction.hasChildWithText(@StringRes textId: Int): ViewInteraction = check(matches(hasDescendant(withText(textId))))

fun ViewInteraction.hasChildWithText(text: String): ViewInteraction = check(matches(hasDescendant(withText(text))))

fun ViewInteraction.doesNotHaveChildWithText(@StringRes textId: Int): ViewInteraction = check(matches(not(hasDescendant(withText(textId)))))

fun ViewInteraction.doesNotHaveChildWithText(text: String): ViewInteraction = check(matches(not(hasDescendant(withText(text)))))

fun ViewInteraction.isSelected(): ViewInteraction = check(ViewAssertions.matches(ViewMatchers.isSelected()))

fun ViewInteraction.isNotSelected(): ViewInteraction = check(ViewAssertions.matches(not(ViewMatchers.isSelected())))

fun ViewInteraction.textInputEditTextHasHint(@StringRes textId: Int): ViewInteraction = check(matches(TextInputEditTextHintMatcher(textId)))

fun ViewInteraction.editTextHasError(@StringRes textId: Int): ViewInteraction = check(matches(EditTextErrorMatcher(textId)))

fun ViewInteraction.hasChildCount(count: Int): ViewInteraction = check(matches(containerHasChildCount(count)))

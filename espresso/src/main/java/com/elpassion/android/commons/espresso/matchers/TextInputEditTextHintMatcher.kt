package com.elpassion.android.commons.espresso.matchers

import android.support.annotation.StringRes
import android.support.design.widget.TextInputLayout
import android.view.View
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

class TextInputEditTextHintMatcher(@StringRes private val textId: Int) : TypeSafeMatcher<View>(View::class.java) {

    override fun matchesSafely(view: View): Boolean {
        return matchTextInputLayoutHint(view, view.context.getString(textId))
    }

    private fun matchTextInputLayoutHint(view: View, expectedHint: String): Boolean {
        val parent = view.parent
        return when (parent) {
            is TextInputLayout -> parent.hint == expectedHint
            is View -> matchTextInputLayoutHint(parent, expectedHint)
            else -> false
        }
    }

    override fun describeTo(description: Description) {
        description.appendText("has hint text from string resource on TextInputLayout: $textId")
    }
}
package com.elpassion.android.commons.espresso.matchers

import android.view.View
import androidx.annotation.StringRes
import com.google.android.material.textfield.TextInputLayout
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

class EditTextErrorMatcher(
        @StringRes private val textId: Int
) : TypeSafeMatcher<View>(View::class.java) {

    override fun matchesSafely(view: View): Boolean {
        val expectedErrorText = view.context.getString(textId)
        return matchErrorText(view, expectedErrorText)
    }

    private fun matchErrorText(view: View, expectedErrorText: String): Boolean {
        val parent = view.parent
        return when (parent) {
            is TextInputLayout -> parent.error == expectedErrorText
            is View -> matchErrorText(parent, expectedErrorText)
            else -> false
        }
    }

    override fun describeTo(description: Description) {
        description.appendText("has error text from string resource on TextInputLayout: $textId")
    }
}
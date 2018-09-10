package com.elpassion.android.commons.espresso

import android.os.Bundle
import androidx.test.rule.ActivityTestRule
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import junit.framework.AssertionFailedError
import org.junit.Rule
import org.junit.Test

class TextInputEditTextHasHintAssertionTest {

    @JvmField @Rule
    val activityRule = ActivityTestRule(Activity::class.java)

    @Test
    fun shouldConfirmTextInputEditTextHasHint() {
        onId(anId).textInputEditTextHasHint(textId)
    }

    @Test(expected = AssertionFailedError::class)
    fun shouldFailToMatch() {
        onId(anId).textInputEditTextHasHint(R.string.non_existing)
    }

    class Activity : android.app.Activity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(TextInputLayout(this).apply {
                addView(TextInputEditText(this.context).apply {
                    id = anId
                    hint = context.getString(textId)
                })
            })
        }
    }

    companion object {
        private val anId = R.id.first
        private val textId = R.string.existing
    }
}
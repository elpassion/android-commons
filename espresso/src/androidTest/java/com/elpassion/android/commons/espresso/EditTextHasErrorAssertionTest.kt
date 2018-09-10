package com.elpassion.android.commons.espresso

import android.os.Bundle
import android.widget.FrameLayout
import androidx.test.rule.ActivityTestRule
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import junit.framework.AssertionFailedError
import org.junit.Rule
import org.junit.Test

class EditTextHasErrorAssertionTest {

    @JvmField @Rule
    val activityRule = ActivityTestRule(Activity::class.java)

    @Test
    fun shouldConfirmEditTextHasError() {
        onId(firstId).editTextHasError(textRes)
    }

    @Test(expected = AssertionFailedError::class)
    fun shouldFailConfirmEditTextHasError() {
        onId(secondId).editTextHasError(textRes)
    }

    class Activity : android.app.Activity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(FrameLayout(this).apply {
                addView(TextInputLayout(context).apply {
                    addView(TextInputEditText(context).apply {
                        id = firstId
                    })
                    error = context.getString(textRes)
                })
                addView(TextInputLayout(context).apply {
                    addView(TextInputEditText(context).apply {
                        id = secondId
                    })
                })
            })
        }
    }

    companion object {
        private val firstId = R.id.first
        private val secondId = R.id.second
        private val textRes = R.string.existing
    }
}
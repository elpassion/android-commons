package com.elpassion.android.commons.espresso

import android.os.Bundle
import android.support.test.rule.ActivityTestRule
import android.widget.Button
import android.widget.FrameLayout
import com.elpassion.android.commons.espresso.test.R
import org.junit.Rule
import org.junit.Test

class HasTextAssertionsTest {

    @JvmField @Rule
    val activityRule = ActivityTestRule(Activity::class.java)

    @Test
    fun shouldConfirmHasText() {
        onId(firstId).hasText(firstText)
    }

    @Test(expected = AssertionError::class)
    fun shouldFailConfirmHasText() {
        onId(firstId).hasText("not existing text")
    }

    @Test
    fun shouldConfirmHasTextFromRes() {
        onId(secondId).hasText(secondTextRes)
    }

    @Test(expected = AssertionError::class)
    fun shouldFailConfirmHasTextFromRes() {
        onId(secondId).hasText(R.string.non_existing)
    }

    class Activity : android.app.Activity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(FrameLayout(this).apply {
                addView(Button(this.context).apply {
                    id = firstId
                    text = firstText
                })
                addView(Button(this.context).apply {
                    id = secondId
                    setText(secondTextRes)
                })
            })
        }
    }

    companion object {
        private val firstId = R.id.first
        private val firstText = "text"
        private val secondId = R.id.second
        private val secondTextRes = R.string.existing
    }
}
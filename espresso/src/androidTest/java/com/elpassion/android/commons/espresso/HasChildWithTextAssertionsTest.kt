package com.elpassion.android.commons.espresso

import android.os.Bundle
import android.support.test.rule.ActivityTestRule
import android.widget.Button
import android.widget.FrameLayout
import com.elpassion.android.commons.espresso.test.R
import org.junit.Rule
import org.junit.Test

class HasChildWithTextAssertionsTest {

    @JvmField @Rule
    val activityRule = ActivityTestRule(Activity::class.java)

    @Test
    fun shouldConfirmHasChildWithText() {
        onId(anId).hasChildWithText(firstText)
    }

    @Test(expected = AssertionError::class)
    fun shouldFailConfirmHasChildWithText() {
        onText(firstText).hasChildWithText(firstText)
    }

    @Test
    fun shouldConfirmHasChildWithTextRes() {
        onId(anId).hasChildWithText(secondTextRes)
    }

    @Test(expected = AssertionError::class)
    fun shouldFailConfirmHasChildWithTextRes() {
        onText(secondTextRes).hasChildWithText(secondTextRes)
    }

    @Test
    fun shouldConfirmDoesNotHaveChildWithText() {
        onId(anId).doesNotHaveChildWithText("not existing text")
    }

    @Test(expected = AssertionError::class)
    fun shouldFailConfirmDoesNotHaveChildWithText() {
        onId(anId).doesNotHaveChildWithText(firstText)
    }

    @Test
    fun shouldConfirmDoesNotHaveChildWithTextRes() {
        onId(anId).doesNotHaveChildWithText(R.string.non_existing)
    }

    @Test(expected = AssertionError::class)
    fun shouldFailConfirmDoesNotHaveChildWithTextRes() {
        onId(anId).doesNotHaveChildWithText(secondTextRes)
    }

    class Activity : android.app.Activity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(FrameLayout(this).apply {
                id = anId
                addView(Button(this.context).apply {
                    text = firstText
                })
                addView(Button(this.context).apply {
                    setText(secondTextRes)
                })
            })
        }
    }

    companion object {
        private val anId = R.id.first
        private val firstText = "text"
        private val secondTextRes = R.string.existing
    }
}
package com.elpassion.android.commons.espresso

import android.os.Bundle
import android.support.test.rule.ActivityTestRule
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import com.elpassion.android.commons.espresso.test.R
import org.junit.Rule
import org.junit.Test

class VisibilityAssertionsTest {

    @JvmField @Rule
    val activityRule = ActivityTestRule(Activity::class.java)

    @Test
    fun shouldConfirmIsDisplayedWhenVisible() {
        onId(firstId).isDisplayed()
    }

    @Test(expected = AssertionError::class)
    fun shouldFailConfirmIsDisplayedWhenVisible() {
        onId(secondId).isDisplayed()
    }

    @Test
    fun shouldConfirmIsNotDisplayedWhenGone() {
        onId(secondId).isNotDisplayed()
        onId(secondId).isGone()
    }

    @Test
    fun shouldConfirmIsNotDisplayedWhenInvisible() {
        onId(thirdId).isNotDisplayed()
    }

    @Test
    fun shouldConfirmDoesNotExists() {
        onId(notExistingId).doesNotExist()
    }

    @Test(expected = AssertionError::class)
    fun shouldFailConfirmIsNotDisplayedWhenVisible() {
        onId(firstId).isNotDisplayed()
    }

    @Test(expected = AssertionError::class)
    fun shouldFailConfirmIsGoneWhenVisible() {
        onId(firstId).isGone()
    }

    class Activity : android.app.Activity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(FrameLayout(this).apply {
                addView(Button(this.context).apply {
                    id = firstId
                    visibility = View.VISIBLE
                })
                addView(Button(this.context).apply {
                    id = secondId
                    visibility = View.GONE
                })
                addView(Button(this.context).apply {
                    id = thirdId
                    visibility = View.INVISIBLE
                })
            })
        }
    }

    companion object {
        private val firstId = R.id.first
        private val secondId = R.id.second
        private val thirdId = R.id.third
        private val notExistingId = R.id.fourth
    }
}
package com.elpassion.android.commons.espresso

import android.os.Bundle
import android.support.test.rule.ActivityTestRule
import android.widget.Button
import android.widget.FrameLayout
import com.elpassion.android.commons.espresso.test.R
import org.junit.Rule
import org.junit.Test

class SelectedAssertionsTest {

    @JvmField @Rule
    val activityRule = ActivityTestRule(Activity::class.java)

    @Test
    fun shouldConfirmIsSelected() {
        onId(firstId).isSelected()
    }

    @Test(expected = AssertionError::class)
    fun shouldFailConfirmIsSelected() {
        onId(secondId).isSelected()
    }

    @Test
    fun shouldConfirmIsNotSelected() {
        onId(secondId).isNotSelected()
    }

    @Test(expected = AssertionError::class)
    fun shouldFailConfirmIsNotSelected() {
        onId(firstId).isNotSelected()
    }

    class Activity : android.app.Activity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(FrameLayout(this).apply {
                addView(Button(this.context).apply {
                    id = firstId
                    isSelected = true
                })
                addView(Button(this.context).apply {
                    id = secondId
                    isSelected = false
                })
            })
        }
    }

    companion object {
        private val firstId = R.id.first
        private val secondId = R.id.second
    }
}
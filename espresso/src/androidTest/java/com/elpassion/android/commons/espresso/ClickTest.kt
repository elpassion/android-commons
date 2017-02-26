package com.elpassion.android.commons.espresso

import android.os.Bundle
import android.support.test.rule.ActivityTestRule
import android.widget.Button
import android.widget.FrameLayout
import org.junit.Rule
import org.junit.Test

class ClickTest {

    @JvmField @Rule
    val activityRule = ActivityTestRule(Activity::class.java)

    @Test
    fun shouldCheckIfButtonNotClicked() {
        onText(clicked).doesNotExist()
    }

    @Test
    fun shouldClickOnButton() {
        onText(notClicked).click()
        onText(clicked).isDisplayed()
    }

    class Activity : android.app.Activity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(FrameLayout(this).apply {
                addView(Button(this.context).apply {
                    text = notClicked
                    setOnClickListener {
                        text = clicked
                    }
                })
            })
        }
    }

    companion object {
        private val notClicked = "Not clicked"
        private val clicked = "Clicked"
    }
}
package com.elpassion.android.commons.espresso

import android.os.Bundle
import android.support.test.rule.ActivityTestRule
import android.widget.EditText
import android.widget.FrameLayout
import org.junit.Rule
import org.junit.Test

class ReplaceTextTest {

    @JvmField @Rule
    val activityRule = ActivityTestRule(Activity::class.java)

    @Test
    fun shouldCheckIfTextNotReplaced() {
        onText(text).doesNotExist()
    }

    @Test
    fun shouldReplaceText() {
        onId(editText).replaceText(text)
        onText(text).isDisplayed()
    }

    class Activity : android.app.Activity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(FrameLayout(this).apply {
                addView(EditText(this.context).apply {
                    id = editText
                })
            })
        }
    }

    companion object {
        private val editText = 123
        private val text = "text"
    }
}
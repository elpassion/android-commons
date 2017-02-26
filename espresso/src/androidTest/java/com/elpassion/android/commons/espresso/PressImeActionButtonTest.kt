package com.elpassion.android.commons.espresso

import android.os.Bundle
import android.support.test.rule.ActivityTestRule
import android.widget.EditText
import android.widget.FrameLayout
import org.junit.Rule
import org.junit.Test

class PressImeActionButtonTest {

    @JvmField @Rule
    val activityRule = ActivityTestRule(Activity::class.java)

    @Test
    fun shouldCheckIfImeActionNotPressed() {
        onText(clicked).doesNotExist()
    }

    @Test
    fun shouldPressImeAction() {
        onId(editText).typeText(text).pressImeActionButton()
        onText(clicked).isDisplayed()
    }

    class Activity : android.app.Activity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(FrameLayout(this).apply {
                addView(EditText(this.context).apply {
                    id = editText
                    setOnEditorActionListener { textView, keyCode, event ->
                        setText(clicked)
                        true
                    }
                })
            })
        }
    }

    companion object {
        private val editText = 123
        private val text = "text"
        private val clicked = "clicked"
    }
}
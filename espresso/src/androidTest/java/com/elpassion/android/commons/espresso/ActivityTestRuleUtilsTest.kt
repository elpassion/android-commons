package com.elpassion.android.commons.espresso

import android.os.Bundle
import android.support.test.rule.ActivityTestRule
import android.widget.TextView
import org.junit.Rule
import org.junit.Test

class ActivityTestRuleUtilsTest {

    @JvmField @Rule
    val activityRule = ActivityTestRule(CheckedAssertionsTest.Activity::class.java, false, false)

    @Test(expected = RuntimeException::class)
    fun shouldFailWhenActivityNotStarted() {
        onText("text").doesNotExist()
    }

    class Activity : android.app.Activity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(TextView(this).apply {
                text = intent.getStringExtra("TEXT_KEY")
            })
        }
    }
}
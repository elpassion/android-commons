package com.elpassion.android.commons.espresso

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.test.rule.ActivityTestRule
import org.junit.Assert.assertNull
import org.junit.Rule
import org.junit.Test

class ActivityTestRuleUtilsTest {

    @JvmField @Rule
    val activityRule = ActivityTestRule(Activity::class.java, false, false)

    @Test
    fun shouldNotStartActivity() {
        assertNull(activityRule.activity)
    }

    @Test
    fun shouldStartActivity() {
        activityRule.startActivity()
        onText("text").doesNotExist()
    }

    @Test
    fun shouldStartActivityWithIntent() {
        activityRule.startActivity(Intent().apply { putExtra("TEXT_KEY", "text") })
        onText("text").isDisplayed()
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
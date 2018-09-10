package com.elpassion.android.commons.espresso

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import androidx.test.rule.ActivityTestRule
import junit.framework.AssertionFailedError
import org.junit.Rule
import org.junit.Test

class InitIntentsRuleTest {

    @JvmField @Rule
    val activityRule = ActivityTestRule(Activity::class.java)

    @JvmField @Rule
    val intentsRule = InitIntentsRule()

    @Test
    fun shouldCheckIntent() {
        checkIntent(StartingActivity::class.java)
    }

    @Test(expected = AssertionFailedError::class)
    fun shouldFailCheckIntent() {
        checkIntent(NotStartingActivity::class.java)
    }

    @Test
    fun shouldCheckNoIntent() {
        checkNoIntent(NotStartingActivity::class.java)
    }

    @Test(expected = AssertionFailedError::class)
    fun shouldFailCheckNoIntent() {
        checkNoIntent(StartingActivity::class.java)
    }

    class Activity : android.app.Activity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(FrameLayout(this))
            startActivity(Intent(this, StartingActivity::class.java))
        }
    }

    class StartingActivity : android.app.Activity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(FrameLayout(this))
        }
    }

    class NotStartingActivity : android.app.Activity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(FrameLayout(this))
        }
    }
}

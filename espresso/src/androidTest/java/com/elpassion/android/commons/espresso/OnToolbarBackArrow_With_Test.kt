package com.elpassion.android.commons.espresso

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test

class OnToolbarBackArrow_With_Test {

    @JvmField @Rule
    val activityRule = ActivityTestRule(Activity::class.java)

    @Test
    fun shouldFindToolbarBackArrow() {
        onToolbarBackArrow().isDisplayed()
    }

    class Activity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(FrameLayout(this))
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
    }
}
package com.elpassion.android.commons.espresso

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test

class OnToolbarBackArrow_Without_Test {

    @JvmField @Rule
    val activityRule = ActivityTestRule(Activity::class.java)

    @Test
    fun shouldNotFindToolbarBackArrow() {
        onToolbarBackArrow().doesNotExist()
    }

    class Activity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(FrameLayout(this))
        }
    }
}
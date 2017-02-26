package com.elpassion.android.commons.espresso

import android.os.Bundle
import android.support.test.rule.ActivityTestRule
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import com.elpassion.android.commons.espresso.test.R
import junit.framework.AssertionFailedError
import org.junit.Rule
import org.junit.Test

class ParentGoneChildVisiblityAssertionTest {

    @JvmField @Rule
    val activityRule = ActivityTestRule(Activity::class.java)

    @Test
    fun shouldBeGoneWhenParentGone() {
        onId(R.id.first).isGone()
    }

    @Test(expected = AssertionFailedError::class)
    fun shouldNotBeGoneWhenParentInvisible() {
        onId(R.id.second).isGone()
    }

    class Activity : android.app.Activity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(FrameLayout(this).apply {
                addView(FrameLayout(context).apply {
                    visibility = View.GONE
                    addView(Button(context).apply {
                        id = R.id.first
                        visibility = View.VISIBLE
                    })
                })
                addView(FrameLayout(context).apply {
                    visibility = View.INVISIBLE
                    addView(Button(context).apply {
                        id = R.id.second
                        visibility = View.VISIBLE
                    })
                })
            })
        }
    }
}

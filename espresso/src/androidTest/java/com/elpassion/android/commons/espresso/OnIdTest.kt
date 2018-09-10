package com.elpassion.android.commons.espresso


import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test

class OnIdTest {

    @JvmField @Rule
    val activityRule = ActivityTestRule(Activity::class.java)

    @Test
    fun shouldNotFindViewById() {
        onId(notExistingId).doesNotExist()
    }

    @Test
    fun shouldFindViewById() {
        // tag::espresso-on-id-test[]
        onId(existingId).isDisplayed()
        // end::espresso-on-id-test[]
    }

    class Activity : android.app.Activity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(FrameLayout(this).apply {
                addView(Button(this.context).apply {
                    id = existingId
                })
            })
        }
    }

    companion object {
        private val notExistingId = R.id.first
        private val existingId = R.id.second
    }
}
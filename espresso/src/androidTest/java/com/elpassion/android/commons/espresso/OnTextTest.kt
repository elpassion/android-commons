package com.elpassion.android.commons.espresso

import android.os.Bundle
import android.support.test.rule.ActivityTestRule
import android.widget.Button
import android.widget.FrameLayout
import com.elpassion.android.commons.espresso.test.R
import org.junit.Rule
import org.junit.Test

class OnTextTest {

    @JvmField @Rule
    val activityRule = ActivityTestRule(Activity::class.java)

    @Test
    fun shouldNotFindViewWithText() {
        onText(notExistingText).doesNotExist()
    }

    @Test
    fun shouldFindViewWithText() {
        onText(existingText).isDisplayed()
    }

    @Test
    fun shouldNotFindViewWithTextResource() {
        onText(notExistingTextResource).doesNotExist()
    }

    @Test
    fun shouldFindViewWithTextResource() {
        onText(existingTextResource).isDisplayed()
    }

    class Activity : android.app.Activity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(FrameLayout(this).apply {
                addView(Button(this.context).apply {
                    text = existingText
                })
                addView(Button(this.context).apply {
                    setText(existingTextResource)
                })
            })
        }
    }

    companion object {
        private val notExistingText = "not existing"
        private val existingText = "existing"
        private val notExistingTextResource = R.string.non_existing
        private val existingTextResource = R.string.existing
    }
}
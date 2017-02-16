package com.elpassion.android.commons.espresso

import android.app.Activity
import android.os.Bundle
import android.support.test.rule.ActivityTestRule
import android.widget.FrameLayout
import android.widget.ImageView
import com.elpassion.android.commons.espresso.test.R
import junit.framework.AssertionFailedError
import org.junit.Rule
import org.junit.Test

class HasImageTest {

    @JvmField @Rule
    val activityRule = ActivityTestRule(FakeActivity::class.java)

    @Test
    fun shouldConfirmHavingLetterP() {
        onId(R.id.first).hasImage(R.drawable.letter_p)
    }

    @Test(expected = AssertionFailedError::class)
    fun shouldFailOnAnyOtherLetter() {
        onId(R.id.first).hasImage(R.drawable.letter_q)
    }

    @Test(expected = AssertionFailedError::class)
    fun shouldFailOnNotHavingLetterP() {
        onId(R.id.first).doesNotHaveImage(R.drawable.letter_p)
    }

    @Test
    fun shouldConfirmNotHavingAnyOtherLetter() {
        onId(R.id.first).doesNotHaveImage(R.drawable.letter_q)
    }

    @Test
    fun shouldConfirmHavingAnyImage() {
        onId(R.id.first).hasAnyImage()
    }

    @Test(expected = AssertionFailedError::class)
    fun shouldFailWhenItDoesNotHaveImage() {
        onId(R.id.second).hasAnyImage()
    }

    @Test(expected = AssertionFailedError::class)
    fun shouldFailWhenItHasAnImage() {
        onId(R.id.first).doesNotHaveAnyImage()
    }

    @Test
    fun shouldConfirmNotHavingAnyImage() {
        onId(R.id.second).doesNotHaveAnyImage()
    }

    class FakeActivity : Activity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(FrameLayout(this).apply {
                addView(ImageView(context).apply {
                    id = R.id.first
                    setImageResource(R.drawable.letter_p)
                })
                addView(ImageView(context).apply {
                    id = R.id.second
                })
            })
        }
    }
}

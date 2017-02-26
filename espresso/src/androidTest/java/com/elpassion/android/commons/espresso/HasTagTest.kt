package com.elpassion.android.commons.espresso

import android.app.Activity
import android.os.Bundle
import android.support.test.rule.ActivityTestRule
import android.widget.Button
import android.widget.FrameLayout
import com.elpassion.android.commons.espresso.test.R
import junit.framework.AssertionFailedError
import org.hamcrest.core.IsEqual.equalTo
import org.hamcrest.core.IsNull.nullValue
import org.junit.Rule
import org.junit.Test

class HasTagTest {

    @JvmField @Rule
    val activityRule = ActivityTestRule(FakeActivity::class.java)

    @Test
    fun shouldConfirmHasTestTag() {
        onId(R.id.first).hasTag(equalTo(testTag))
    }

    @Test(expected = AssertionFailedError::class)
    fun shouldFailWithNullTagMatcher() {
        onId(R.id.first).hasTag(nullValue())
    }

    @Test(expected = AssertionFailedError::class)
    fun shouldFailWithAnyOtherTag() {
        onId(R.id.first).hasTag(equalTo("anyOtherTag"))
    }

    @Test
    fun shouldConfirmHasNullTag() {
        onId(R.id.second).hasTag(nullValue())
    }

    @Test(expected = AssertionFailedError::class)
    fun shouldFailWithTestTag() {
        onId(R.id.second).hasTag(equalTo(testTag))
    }

    class FakeActivity : Activity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(FrameLayout(this).apply {
                addView(Button(this.context).apply {
                    id = R.id.first
                    tag = testTag
                })
                addView(Button(this.context).apply {
                    id = R.id.second
                })
            })
        }
    }

    companion object {
        private val testTag = "tag"
    }
}

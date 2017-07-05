package com.elpassion.android.commons.espresso

import android.os.Bundle
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.elpassion.android.commons.espresso.matchers.withParentId
import com.elpassion.android.commons.espresso.test.R
import org.junit.Rule
import org.junit.Test

class OnChildWithIdTest {

    @JvmField @Rule
    val activityRule = ActivityTestRule(Activity::class.java)

    @Test
    fun shouldNotFindWhenParentNotExisting() {
        onView(withId(childExistingId).withParentId(parentNotExistingId)).doesNotExist()
    }

    @Test
    fun shouldNotFindWhenChildNotExisting() {
        onView(withId(childNotExistingId).withParentId(parentFirstExistingId)).doesNotExist()
    }

    @Test
    fun shouldFindFirstChildWhenParentAndChildExisting() {
        onView(withId(childExistingId).withParentId(parentFirstExistingId)).isDisplayed()
    }

    @Test
    fun shouldFindSecondChildWhenParentAndChildExisting() {
        onView(withId(childExistingId).withParentId(parentSecondExistingId)).isDisplayed()
    }

    @Test
    fun shouldNotDisplayThirdChildWhenParentIsGone() {
        onView(withId(childExistingId).withParentId(parentThirdExistingId)).isNotDisplayed()
    }

    class Activity : android.app.Activity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(FrameLayout(this).apply {
                addView(LinearLayout(context).apply {
                    id = parentFirstExistingId
                    addView(View(context).apply {
                        id = childExistingId
                    })
                })
                addView(LinearLayout(context).apply {
                    id = parentSecondExistingId
                    addView(View(context).apply {
                        id = childExistingId
                    })
                })
                addView(LinearLayout(context).apply {
                    id = parentThirdExistingId
                    visibility = View.GONE
                    addView(View(context).apply {
                        id = childExistingId
                    })
                })
            })
        }
    }

    companion object {
        private val parentNotExistingId = R.id.first
        private val childNotExistingId = R.id.second
        private val parentFirstExistingId = R.id.third
        private val parentSecondExistingId = R.id.fourth
        private val parentThirdExistingId = R.id.fifth
        private val childExistingId = R.id.sixth
    }
}
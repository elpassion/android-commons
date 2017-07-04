package com.elpassion.android.commons.espresso

import android.os.Bundle
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
        withParentId(parentNotExistingId).onChildWithId(childExistingId).doesNotExist()
    }

    @Test
    fun shouldNotFindWhenChildNotExisting() {
        withParentId(parentFirstExistingId).onChildWithId(childNotExistingId).doesNotExist()
    }

    @Test
    fun shouldFindFirstChildWhenParentAndChildExisting() {
        withParentId(parentFirstExistingId).onChildWithId(childExistingId).isDisplayed()
    }

    @Test
    fun shouldFindSecondChildWhenParentAndChildExisting() {
        withParentId(parentSecondExistingId).onChildWithId(childExistingId).isDisplayed()
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
            })
        }
    }

    companion object {
        private val parentNotExistingId = R.id.first
        private val childNotExistingId = R.id.second
        private val parentFirstExistingId = R.id.third
        private val parentSecondExistingId = R.id.fourth
        private val childExistingId = R.id.sixth
    }
}
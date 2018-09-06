package com.elpassion.android.view

import android.view.View
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import junit.framework.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ViewTest {

    private val context = InstrumentationRegistry.getContext()

    @Test
    fun testShouldSetViewVisibilityToVisible() {
        val view = View(context)
        view.visibility = View.INVISIBLE
        // tag::view-show[]
        view.show()
        // end::view-show[]
        assertEquals(View.VISIBLE, view.visibility)
    }

    @Test
    fun testShouldSetViewVisibilityToGone() {
        val view = View(context)
        // tag::view-hide[]
        view.hide()
        // end::view-hide[]
        assertEquals(View.GONE, view.visibility)
    }

    fun testShouldReturnThatViewIsVisibleWhenViewIsVisible() {
        val view = View(context)
        assertTrue(view.isVisible)
    }

    fun testShouldReturnThatViewIsInvisibleWhenIsGone() {
        val view = View(context)
        view.visibility = View.GONE
        assertFalse(view.isVisible)
    }

    fun testShouldReturnThatViewIsInvisibleWhenIsInvisible() {
        val view = View(context)
        view.visibility = View.INVISIBLE
        assertFalse(view.isVisible)
    }

    fun testShouldViewHaveVisibleStateWhenIsVisibleSetTrue() {
        val view = View(context)
        // tag::view-is-visible-set[]
        view.isVisible = true
        // end::view-is-visible-set[]
        assertTrue(view.visibility == View.VISIBLE)
    }

    fun testShouldViewHaveGoneStateWhenIsVisibleSetFalse() {
        val view = View(context)
        view.isVisible = false
        assertTrue(view.visibility == View.GONE)
    }

    fun testShouldEnableDisabledView() {
        val view = View(context).apply { isEnabled = false }
        // tag::view-enable[]
        view.enable()
        // end::view-enable[]
        assertTrue(view.isEnabled)
    }

    fun testShouldDisableEnabledView() {
        val view = View(context)
        // tag::view-disable[]
        view.disable()
        // end::view-disable[]
        assertFalse(view.isEnabled)
    }
}
package com.elpassion.android.view

import android.app.Application
import android.test.ApplicationTestCase
import android.view.View

class ViewTest : ApplicationTestCase<Application>(Application::class.java) {

    fun testShouldSetViewVisibilityToVisible() {
        val view = View(context)
        view.visibility = View.INVISIBLE
        // tag::view-show[]
        view.show()
        // end::view-show[]
        assertEquals(View.VISIBLE, view.visibility)
    }

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
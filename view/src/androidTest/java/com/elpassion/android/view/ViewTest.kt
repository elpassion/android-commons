package com.elpassion.android.view

import android.app.Application
import android.test.ApplicationTestCase
import android.view.View

class ViewTest : ApplicationTestCase<Application>(Application::class.java) {

    fun testShouldSetViewVisibilityToVisible() {
        val view = View(context)
        view.visibility = View.INVISIBLE
        view.show()
        assertEquals(View.VISIBLE, view.visibility)
    }

    fun testShouldSetViewVisibilityToGone() {
        val view = View(context)
        view.hide()
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

    fun testShouldEnableDisabledView() {
        val view = View(context).apply { isEnabled = false }
        view.enable()
        assertTrue(view.isEnabled)
    }

    fun testShouldDisableEnabledView() {
        val view = View(context)
        view.disable()
        assertFalse(view.isEnabled)
    }
}
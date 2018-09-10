package com.elpassion.android.commons.espresso.recycler

import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

fun containerHasChildCount(count: Int): Matcher<View> = object : TypeSafeMatcher<View>() {

    public override fun matchesSafely(view: View): Boolean {
        val groupSize = getGroupCount(view)
        return groupSize == count
    }

    private fun getGroupCount(view: View): Int = when (view) {
        is RecyclerView -> view.adapter?.itemCount
        is AdapterView<*> -> view.adapter?.count
        is ViewGroup -> view.childCount
        else -> throw IllegalArgumentException("Unknown view type")
    } ?: throw IllegalStateException("View adapter can't be null")

    override fun describeTo(description: Description) {
        description.appendText("ViewGroup has child count: $count")
    }
}

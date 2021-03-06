package com.elpassion.android.commons.espresso.recycler

import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.AllOf.allOf
import org.hamcrest.core.AnyOf.anyOf

fun onRecyclerViewItem(@IdRes recyclerViewId: Int, itemPosition: Int, @IdRes viewId: Int): ViewInteraction {
    return onRecyclerViewItem(recyclerViewId, itemPosition, withId(viewId))
}

private fun onRecyclerViewItem(
        @IdRes recyclerViewId: Int,
        itemPosition: Int,
        viewMatcher: Matcher<View>
): ViewInteraction = onView(allOf(
        anyOf(
                atPosition(recyclerViewId, itemPosition),
                isDescendantOfA(atPosition(recyclerViewId, itemPosition))),
        viewMatcher
))

private fun atPosition(
        parentId: Int,
        itemPosition: Int
): Matcher<View>? = allOf(
        withParent(withId(parentId)),
        atPosition(itemPosition)
)


private fun atPosition(position: Int) = object : TypeSafeMatcher<View>() {

    override fun matchesSafely(item: View): Boolean {
        val parent = item.parent as? RecyclerView ?: return false
        val layoutManager = parent.layoutManager
        val viewAtPosition = layoutManager?.findViewByPosition(position)
        return item == viewAtPosition
    }

    override fun describeTo(description: Description) {
        description.appendText("view at position #$position")
    }
}
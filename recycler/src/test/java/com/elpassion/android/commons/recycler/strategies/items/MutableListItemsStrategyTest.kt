package com.elpassion.android.commons.recycler.strategies.items

import com.elpassion.android.commons.recycler.components.base.MutableListItemsStrategy
import org.junit.Assert.assertEquals
import org.junit.Test

class MutableListItemsStrategyTest {

    @Test
    fun shouldRemoveItem() {
        val strategy = MutableListItemsStrategy(listOf("A", "B", "C"))
        strategy.remove("B")

        assertEquals(listOf("A", "C"), strategy.allItems())
    }
}
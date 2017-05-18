package com.elpassion.android.commons.recycler.strategies.items

import com.elpassion.android.commons.recycler.components.group.impl.MutableMapItemsStrategy
import org.junit.Assert.assertEquals
import org.junit.Test

class MutableMapItemsStrategyTest {

    @Test
    fun shouldRemoveSection() {
        val items = mutableMapOf(
                1 to mutableListOf(1, 2),
                2 to mutableListOf(3, 4))
        val strategy = MutableMapItemsStrategy(items)
        strategy.remove(1)

        assertEquals(mutableListOf(3, 4), strategy.allItems())
    }
}
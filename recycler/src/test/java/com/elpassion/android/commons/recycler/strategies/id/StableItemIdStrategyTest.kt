package com.elpassion.android.commons.recycler.strategies.id

import android.support.v7.widget.RecyclerView
import android.view.View
import com.elpassion.android.commons.recycler.components.base.ListItemsStrategy
import com.elpassion.android.commons.recycler.components.stable.StableItemAdapter
import com.elpassion.android.commons.recycler.components.stable.getStableItemIdentifier
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when` as whenever

class StableItemIdStrategyTest {

    @Test
    fun shouldReturnItemIdFromItemStrategyBasedOnPosition() {
        val strategy = getStableItemIdentifier(ListItemsStrategy(listOf(
                createItemAdapter(stableId = 100),
                createItemAdapter(stableId = 101),
                createItemAdapter(stableId = 102))))

        val itemId = strategy(1)
        assertEquals(101, itemId)
    }

    private fun createItemAdapter(stableId: Long) = object : StableItemAdapter(stableId = stableId, viewType = 123) {
        override fun onBindViewHolder(holder: RecyclerView.ViewHolder) {}
    }
}
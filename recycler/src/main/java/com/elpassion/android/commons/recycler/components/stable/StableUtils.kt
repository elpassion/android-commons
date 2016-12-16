package com.elpassion.android.commons.recycler.components.stable

import com.elpassion.android.commons.recycler.RecyclerViewCompositeAdapter
import com.elpassion.android.commons.recycler.components.ItemsStrategy

fun <T : StableItemAdapter> getStableItemIdentifier(itemsStrategy: ItemsStrategy<T>) =
        { position: Int ->
            itemsStrategy.allItems()[position].stableId
        }

fun <T : StableItemAdapter> createStableIdInitialization() =
        { adapter: RecyclerViewCompositeAdapter<T> ->
            adapter.setHasStableIds(true)
        }
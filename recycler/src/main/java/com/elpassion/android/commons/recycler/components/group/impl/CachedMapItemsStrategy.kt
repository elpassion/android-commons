package com.elpassion.android.commons.recycler.components.group.impl

import com.elpassion.android.commons.recycler.components.group.MutableSectionedItemsStrategy

@Deprecated("Use BasicAdapter instead", ReplaceWith("BasicAdapter"))
class CachedMapItemsStrategy<Section, Item>(
        private val itemsStrategy: MutableSectionedItemsStrategy<Section, Item>
) : MutableSectionedItemsStrategy<Section, Item> by itemsStrategy {

    private var cache: List<Item>? = null

    override fun allItems() = cache ?: itemsStrategy.allItems().apply { cache = this }

    override fun addAll(section: Section, from: List<Item>) {
        itemsStrategy.addAll(section, from)
        cache = null
    }

    override fun add(section: Section, item: Item) {
        itemsStrategy.add(section, item)
        cache = null
    }

    override fun clear() {
        itemsStrategy.clear()
        cache = null
    }

    override fun set(section: Section, from: List<Item>) {
        itemsStrategy.set(section, from)
        cache = null
    }

    override fun remove(section: Section) {
        itemsStrategy.remove(section)
        cache = null
    }
}
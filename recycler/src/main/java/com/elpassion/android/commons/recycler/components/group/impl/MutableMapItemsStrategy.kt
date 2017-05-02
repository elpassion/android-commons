package com.elpassion.android.commons.recycler.components.group.impl

import com.elpassion.android.commons.recycler.components.group.MutableSectionedItemsStrategy
import java.util.*

class MutableMapItemsStrategy<Section, Item> : MutableSectionedItemsStrategy<Section, Item> {

    private val items: MutableMap<Section, MutableList<Item>>

    constructor() {
        items = mutableMapOf()
    }

    constructor(list: Map<Section, List<Item>>) {
        items = LinkedHashMap<Section, MutableList<Item>>(list.size).apply {
            putAll(list.mapValues { it.value.toMutableList() })
        }
    }

    override fun addAll(section: Section, from: List<Item>) {
        items[section]!!.addAll(from)
    }

    override fun add(section: Section, item: Item) {
        items[section]!!.add(item)
    }

    override fun clear() {
        items.clear()
    }

    override fun set(section: Section, from: List<Item>) {
        items.put(section, from.toMutableList())
    }

    override fun allItems() = items.values.flatten()

    override fun getSection(section: Section) = items[section]!!.toList()

    override fun getRelativePosition(itemPosition: Int) = getRelativePosition(items, itemPosition)

    override fun getSectionForItemPosition(itemPosition: Int) = getSectionForItem(allItems()[itemPosition], items)

    override fun remove(section: Section) {
        items.remove(section)
    }
}
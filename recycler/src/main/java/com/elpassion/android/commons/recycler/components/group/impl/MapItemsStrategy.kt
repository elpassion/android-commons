package com.elpassion.android.commons.recycler.components.group.impl

import com.elpassion.android.commons.recycler.components.group.SectionedItemsStrategy
import java.util.*

@Deprecated("Use BasicAdapter instead", ReplaceWith("BasicAdapter"))
class MapItemsStrategy<Section, Item>(
        map: Map<Section, List<Item>>
) : SectionedItemsStrategy<Section, Item> {

    private val items: Map<Section, List<Item>> = LinkedHashMap<Section, List<Item>>(map.size).apply {
        putAll(map.mapValues { ArrayList(it.value) })
    }

    override fun allItems() = items.values.flatten()

    override fun getSection(section: Section) = items[section]!!.toList()

    override fun getRelativePosition(itemPosition: Int) = getRelativePosition(items, itemPosition)

    override fun getSectionForItemPosition(itemPosition: Int) = getSectionForItem(allItems()[itemPosition], items)
}


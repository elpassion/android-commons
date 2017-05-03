package com.elpassion.android.commons.recycler.components.group.impl

import com.elpassion.android.commons.recycler.components.group.SectionedItemsStrategy

internal fun <Section, Item> getSectionForItem(
        item: Item,
        items: Map<Section, List<Item>>
): Section
        = items.filterValues { it.contains(item) }.keys.first()

internal fun <Section, Item> SectionedItemsStrategy<Section, Item>.getRelativePosition(
        adapters: Map<Section, List<Item>>,
        itemPosition: Int
): Int {
    val item = allItems()[itemPosition]
    val section = getSectionForItem(item, adapters)
    return getSection(section).indexOf(item)
}
package com.elpassion.android.commons.recycler.components.group

import com.elpassion.android.commons.recycler.components.ItemsStrategy

@Deprecated("Use BasicAdapter instead", ReplaceWith("BasicAdapter"))
interface SectionedItemsStrategy<Section, Item> : ItemsStrategy<Item> {
    fun getSection(section: Section): List<Item>

    fun getRelativePosition(itemPosition: Int): Int

    fun getSectionForItemPosition(itemPosition: Int): Section
}

@Deprecated("Use BasicAdapter instead", ReplaceWith("BasicAdapter"))
interface MutableSectionedItemsStrategy<Section, Item> : SectionedItemsStrategy<Section, Item> {
    fun addAll(section: Section, from: List<Item>)

    fun add(section: Section, item: Item)

    fun clear()

    fun set(section: Section, from: List<Item>)

    fun remove(section: Section)
}
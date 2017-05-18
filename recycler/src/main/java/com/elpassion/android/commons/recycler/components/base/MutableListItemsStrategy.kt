package com.elpassion.android.commons.recycler.components.base

import com.elpassion.android.commons.recycler.components.MutableItemsStrategy

class MutableListItemsStrategy<T> @JvmOverloads constructor(
        list: List<T> = listOf()
) : MutableItemsStrategy<T> {

    private val items: MutableList<T> = list.toMutableList()

    override fun allItems() = items

    override fun addAll(from: List<T>) {
        items.addAll(from)
    }

    override fun add(item: T) {
        items.add(item)
    }

    override fun clear() {
        items.clear()
    }

    override fun set(from: List<T>) {
        items.clear()
        items.addAll(from)
    }

    override fun remove(item: T) {
        items.remove(item)
    }
}
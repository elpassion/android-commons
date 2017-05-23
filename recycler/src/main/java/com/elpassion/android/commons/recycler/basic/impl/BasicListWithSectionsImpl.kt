package com.elpassion.android.commons.recycler.basic.impl

import com.elpassion.android.commons.recycler.basic.BasicListWithSections
import com.elpassion.android.commons.recycler.basic.asBasicMapOfBasicLists

class BasicListWithSectionsImpl<out Item, in Section>(
        private val source: Map<Section, List<Item>>
) : BasicListWithSections<Item, Section> {

    override val sections = source.asBasicMapOfBasicLists()

    override fun get(key: Int): Item {
        var offset = 0
        for (section in source.values) {
            if (key < offset + section.size) {
                return section[key - offset]
            } else {
                offset += section.size
            }
        }
        throw IndexOutOfBoundsException()
    }

    override val size: Int get() = source.map { entry -> entry.value.size }.sum()
}
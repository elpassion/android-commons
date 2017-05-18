package com.elpassion.android.commons.recycler.basic.impl

import com.elpassion.android.commons.recycler.basic.BasicListWithMutableSections
import com.elpassion.android.commons.recycler.basic.BasicMutableList
import com.elpassion.android.commons.recycler.basic.asBasicMutableMap

class BasicListWithMutableSectionsImpl<Item, in Section>(
        private val source: MutableMap<Section, BasicMutableList<Item>>
) : BasicListWithMutableSections<Item, Section> {

    override val sections = source.asBasicMutableMap()

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
package com.elpassion.android.commons.recycler.basic.impl

import com.elpassion.android.commons.recycler.basic.ListWithSections

class ListWithSectionsImpl<out Item, Section>(
        override val sections: Map<Section, List<Item>>
) : ListWithSections<Item, Section>, AbstractList<Item>() {

    override fun get(index: Int): Item {
        var offset = 0
        for (section in sections.values) {
            if (index < offset + section.size) {
                return section[index - offset]
            } else {
                offset += section.size
            }
        }
        throw IndexOutOfBoundsException()
    }

    override val size: Int get() = sections.map { entry -> entry.value.size }.sum()
}
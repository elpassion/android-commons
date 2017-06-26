package com.elpassion.android.commons.recycler.basic

import com.elpassion.android.commons.recycler.basic.impl.ListWithMutableSectionsImpl
import com.elpassion.android.commons.recycler.basic.impl.ListWithSectionsImpl

fun <Item, Section> Map<Section, List<Item>>.asBasicListWithSections(): ListWithSections<Item, Section>
        = ListWithSectionsImpl(this)

fun <Item, Section> MutableMap<Section, MutableList<Item>>.asBasicListWithMutableSections(): ListWithMutableSections<Item, Section>
        = ListWithMutableSectionsImpl(this)
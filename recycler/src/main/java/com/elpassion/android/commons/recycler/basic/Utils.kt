package com.elpassion.android.commons.recycler.basic

import com.elpassion.android.commons.recycler.basic.impl.BasicListWithMutableSectionsImpl
import com.elpassion.android.commons.recycler.basic.impl.BasicListWithSectionsImpl

fun <Item, Section> Map<Section, List<Item>>.asBasicListWithSections(): BasicListWithSections<Item, Section>
        = BasicListWithSectionsImpl(this)

fun <Item, Section> MutableMap<Section, MutableList<Item>>.asBasicListWithMutableSections(): BasicListWithMutableSections<Item, Section>
        = BasicListWithMutableSectionsImpl(this)
package com.elpassion.android.commons.recycler.basic

interface ListWithMutableSections<Item, in Section> : ListWithSections<Item, Section> {
    override val sections: MutableMap<in Section, out MutableList<Item>>
}
package com.elpassion.android.commons.recycler.basic

abstract class ListWithMutableSections<Item, in Section> : ListWithSections<Item, Section>() {
    abstract override val sections: MutableMap<in Section, out MutableList<Item>?>
}
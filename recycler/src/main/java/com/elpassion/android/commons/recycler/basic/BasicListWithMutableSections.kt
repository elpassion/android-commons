package com.elpassion.android.commons.recycler.basic

abstract class BasicListWithMutableSections<Item, in Section> : BasicListWithSections<Item, Section>() {
    abstract override val sections: MutableMap<in Section, out MutableList<Item>?>
}
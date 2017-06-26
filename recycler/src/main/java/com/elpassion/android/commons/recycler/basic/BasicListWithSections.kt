package com.elpassion.android.commons.recycler.basic

abstract class BasicListWithSections<out Item, in Section> : AbstractList<Item>() {
    abstract val sections: Map<in Section, List<Item>?>
}
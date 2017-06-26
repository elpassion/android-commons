package com.elpassion.android.commons.recycler.basic

interface ListWithSections<out Item, in Section> : List<Item> {
    val sections: Map<in Section, List<Item>>
}
package com.elpassion.android.commons.recycler.components

@Deprecated("Use BasicAdapter instead", ReplaceWith("BasicAdapter"))
interface ItemsStrategy<out T> {
    fun allItems(): List<T>
}


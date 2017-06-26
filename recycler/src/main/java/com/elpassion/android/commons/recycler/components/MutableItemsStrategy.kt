package com.elpassion.android.commons.recycler.components

@Deprecated("Use BasicAdapter instead", ReplaceWith("BasicAdapter"))
interface MutableItemsStrategy<T> : ItemsStrategy<T> {
    fun addAll(from: List<T>)

    fun add(item: T)

    fun clear()

    fun set(from: List<T>)

    fun remove(item: T)
}
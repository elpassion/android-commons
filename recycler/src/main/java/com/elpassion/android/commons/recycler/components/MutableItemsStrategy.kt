package com.elpassion.android.commons.recycler.components

interface MutableItemsStrategy<T> : ItemsStrategy<T> {
    fun addAll(from: List<T>)

    fun add(item: T)

    fun clear()

    fun set(from: List<T>)

    fun remove(item: T)
}
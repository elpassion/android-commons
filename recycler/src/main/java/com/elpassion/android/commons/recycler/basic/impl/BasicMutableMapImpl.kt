package com.elpassion.android.commons.recycler.basic.impl

import com.elpassion.android.commons.recycler.basic.BasicMutableMap

class BasicMutableMapImpl<in Key, Value>(
        private val source: MutableMap<Key, Value>
) : BasicMutableMap<Key, Value?> {

    override fun get(key: Key): Value? = source[key]

    override fun set(key: Key, value: Value?) {
        if (value !== null) {
            source[key] = value
        } else {
            source.remove(key)
        }
    }

    override fun clear() = source.clear()
}
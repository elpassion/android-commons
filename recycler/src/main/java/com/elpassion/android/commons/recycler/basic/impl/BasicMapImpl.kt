package com.elpassion.android.commons.recycler.basic.impl

import com.elpassion.android.commons.recycler.basic.BasicMap

class BasicMapImpl<in Key, out Value>(
        private val source: Map<Key, Value>
) : BasicMap<Key, Value?> {

    override fun get(key: Key): Value? = source[key]
}
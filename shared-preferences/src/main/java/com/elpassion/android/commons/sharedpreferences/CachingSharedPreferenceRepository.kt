package com.elpassion.android.commons.sharedpreferences

import java.util.*

class CachingSharedPreferenceRepository<T>(private val repository: SharedPreferenceRepository<T>) : SharedPreferenceRepository<T> {

    private val cacheMap = HashSet<String>()
    private val valueMap = HashMap<String, T?>()

    override fun read(key: String): T? {
        if (!cacheMap.contains(key)) {
            cacheMap.add(key)
            valueMap[key] = repository.read(key)
        }
        return valueMap[key]
    }

    override fun write(key: String, value: T?) {
        cacheMap.add(key)
        valueMap[key] = value
        repository.write(key, value)
    }

    override fun contains(key: String) = cacheMap.contains(key) || repository.contains(key)
}
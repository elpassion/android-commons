package com.elpassion.android.commons.sharedpreferences

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun <T> SharedPreferenceRepository<T>.asProperty(key: String): ReadWriteProperty<Any, T?> = PreferencesProperty(this, key)

private class PreferencesProperty<T>(
        private val sharedPreferenceRepository: SharedPreferenceRepository<T>,
        private val key: String) : ReadWriteProperty<Any, T?> {

    override fun getValue(thisRef: Any, property: KProperty<*>) = sharedPreferenceRepository.read(key)

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T?) {
        sharedPreferenceRepository.write(key, value)
    }
}

fun <T> SharedPreferenceRepository<T>.asPropertyWithDefault(key: String, default: T): ReadWriteProperty<Any, T> = PreferencesPropertyWithDefault(this, key, default)

private class PreferencesPropertyWithDefault<T>(
        private val sharedPreferenceRepository: SharedPreferenceRepository<T>,
        private val key: String,
        private val default: T) : ReadWriteProperty<Any, T> {

    override fun getValue(thisRef: Any, property: KProperty<*>) = sharedPreferenceRepository.read(key) ?: default

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        sharedPreferenceRepository.write(key, value)
    }
}
package com.elpassion.android.commons.sharedpreferences

import android.content.SharedPreferences
import androidx.annotation.WorkerThread
import androidx.core.content.edit
import java.time.Instant
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class DoublePreference(
        private val preferences: SharedPreferences,
        private val name: String,
        private val defaultValue: Double
) : ReadWriteProperty<Any, Double> {

    @WorkerThread
    override fun getValue(thisRef: Any, property: KProperty<*>): Double =
            preferences.getString(name, defaultValue.toString()).toDouble()

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Double) {
        preferences.edit { putString(name, value.toString()) }
    }
}

class BooleanPreference(
        private val preferences: SharedPreferences,
        private val name: String,
        private val defaultValue: Boolean
) : ReadWriteProperty<Any, Boolean> {

    @WorkerThread
    override fun getValue(thisRef: Any, property: KProperty<*>): Boolean {
        return preferences.getBoolean(name, defaultValue)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Boolean) {
        preferences.edit { putBoolean(name, value) }
    }
}

class IntPreference(
        private val preferences: SharedPreferences,
        private val name: String,
        private val defaultValue: Int
) : ReadWriteProperty<Any, Int> {

    @WorkerThread
    override fun getValue(thisRef: Any, property: KProperty<*>): Int {
        return preferences.getInt(name, defaultValue)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Int) {
        preferences.edit { putInt(name, value) }
    }
}

class LongPreference(
        private val preferences: SharedPreferences,
        private val name: String,
        private val defaultValue: Long
) : ReadWriteProperty<Any, Long> {

    @WorkerThread
    override fun getValue(thisRef: Any, property: KProperty<*>): Long {
        return preferences.getLong(name, defaultValue)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Long) {
        preferences.edit { putLong(name, value) }
    }
}


class InstantPreference(
        private val preferences: SharedPreferences,
        private val name: String
) : ReadWriteProperty<Any, Instant> {

    @WorkerThread
    override fun getValue(thisRef: Any, property: KProperty<*>): Instant {
        return Instant.ofEpochMilli(preferences.getLong(name, 0))
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Instant) {
        preferences.edit { putLong(name, value.toEpochMilli()) }
    }
}

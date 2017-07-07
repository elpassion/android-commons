package com.elpassion.android.commons.sharedpreferences

import android.content.SharedPreferences

interface SharedPreferenceRepository<T> {

    fun write(key: String, value: T?)

    fun read(key: String): T?

    fun contains(key: String): Boolean
}

inline fun <reified T> createSharedPrefs(
        noinline sharedPreferencesProvider: () -> SharedPreferences,
        jsonAdapter: JsonConverterAdapter<T>
) = object : SharedPreferenceRepository<T> {

    private val sharedPreferences by lazy(sharedPreferencesProvider)

    override fun write(key: String, value: T?) {
        sharedPreferences.edit()
                .putString(key, jsonAdapter.toJson(value))
                .apply()
    }

    override fun read(key: String): T? = sharedPreferences.getString(key, null)?.let { jsonAdapter.fromJson(it) }

    override fun contains(key: String) = sharedPreferences.contains(key)
}
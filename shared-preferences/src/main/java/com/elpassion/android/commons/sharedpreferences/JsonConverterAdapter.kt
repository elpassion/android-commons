package com.elpassion.android.commons.sharedpreferences

interface JsonConverterAdapter<T> {
    fun toJson(t: T?): String

    fun fromJson(t: String): T?
}
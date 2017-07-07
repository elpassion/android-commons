package com.elpassion.sharedpreferences.moshiadapter

import com.elpassion.android.commons.sharedpreferences.JsonConverterAdapter
import com.squareup.moshi.Moshi
import java.lang.reflect.Type

inline fun <reified T> moshiConverterAdapter(moshi: Moshi = Moshi.Builder().build(), type: Type = T::class.java) = object : JsonConverterAdapter<T> {
    private val moshiTypeAdapter: com.squareup.moshi.JsonAdapter<T> = moshi.adapter(type)

    override fun toJson(t: T?): String = moshiTypeAdapter.toJson(t)

    override fun fromJson(t: String): T? = moshiTypeAdapter.fromJson(t)
}
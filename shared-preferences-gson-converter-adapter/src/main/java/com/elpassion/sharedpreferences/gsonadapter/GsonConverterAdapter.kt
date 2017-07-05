package com.elpassion.sharedpreferences.gsonadapter

import com.elpassion.android.commons.sharedpreferences.JsonConverterAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GsonConverterAdapter<T>(private val gson: Gson = Gson()) : JsonConverterAdapter<T> {
    private val type = object : TypeToken<T>() {}.type

    override fun toJson(t: T?): String = gson.toJson(t, type)

    override fun fromJson(t: String): T? = gson.fromJson<T>(t, type)
}
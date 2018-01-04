package com.elpassion.android.commons.sharedpreferences

// tag::sharedpreferences-json-converter-adapter-interface[]
interface JsonConverterAdapter<T> {
    fun toJson(t: T?): String

    fun fromJson(t: String): T?
}
// end::sharedpreferences-json-converter-adapter-interfacea[]
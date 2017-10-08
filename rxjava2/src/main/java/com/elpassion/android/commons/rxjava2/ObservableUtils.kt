package com.elpassion.android.commons.rxjava2

import io.reactivex.Observable
import kotlin.reflect.KClass

inline fun <reified T : Any> Observable<in T>.type(type: KClass<T>): Observable<T> {
    return this.ofType(type.java)
}
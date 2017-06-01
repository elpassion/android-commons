package com.elpassion.android.commons.rxjava2.test

import io.reactivex.observers.TestObserver
import junit.framework.Assert.assertTrue

fun <T> TestObserver<T>.assertValuesThat(predicate: (T) -> Boolean) {
    assertTrue(values().all(predicate))
}

fun <T> TestObserver<T>.assertValueThat(predicate: (T) -> Boolean) {
    assertTrue(values().first().run(predicate))
}
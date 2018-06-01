package com.elpassion.android.commons.rxjava2.test

import io.reactivex.observers.TestObserver
import org.junit.Assert
import org.junit.Assert.assertTrue

fun <T> TestObserver<T>.assertValuesThat(predicate: (T) -> Boolean) {
    assertTrue(values().all(predicate))
}

fun <T> TestObserver<T>.assertValueThat(predicate: (T) -> Boolean) {
    assertTrue(values().first().run(predicate))
}

fun <T> TestObserver<T>.assertLastValue(expected: T) {
    Assert.assertEquals(expected, values().last())
}

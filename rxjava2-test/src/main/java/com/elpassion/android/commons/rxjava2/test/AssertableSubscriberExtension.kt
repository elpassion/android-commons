package com.elpassion.android.commons.rxjava2.test

import io.reactivex.observers.TestObserver
import junit.framework.Assert.assertTrue
import org.junit.Assert

fun <T> TestObserver<T>.assertValuesThat(predicate: (T) -> Boolean) {
    assertTrue(values().all(predicate))
}

fun <T> TestObserver<T>.assertValueThat(predicate: (T) -> Boolean) {
    assertTrue(values().first().run(predicate))
}

fun <T> TestObserver<T>.assertLastValue(expected: T) {
    Assert.assertEquals(expected, values().last())
}

fun <T> TestObserver<T>.assertLastValueThat(predicate: (T.() -> Boolean)) {
    Assert.assertTrue("${values().last()}\nDoes not match predicate", predicate(values().last()))
}
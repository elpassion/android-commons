package com.elpassion.android.commons.rxjavatest

import junit.framework.Assert.assertTrue
import rx.observers.TestSubscriber

//TODO: CR: Add missing test to ensure correct implementation
fun <T> TestSubscriber<T>.assertValuesThat(predicate: (T) -> Boolean) = assertValueThat(predicate)

fun <T> TestSubscriber<T>.assertValueThat(predicate: (T) -> Boolean) {
    assertTrue(onNextEvents.first().run(predicate))
}
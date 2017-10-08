package com.elpassion.android.commons.rxjavatest

import junit.framework.Assert.assertTrue
import rx.observers.AssertableSubscriber

fun <T> AssertableSubscriber<T>.assertValuesThat(predicate: (T) -> Boolean) {
    assertTrue(onNextEvents.all(predicate))
}

fun <T> AssertableSubscriber<T>.assertValueThat(predicate: (T) -> Boolean) {
    assertTrue(onNextEvents.first().run(predicate))
}
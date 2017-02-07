package com.elpassion.android.commons.rxjavatest

import org.junit.Test
import rx.Observable


class TestSubscriberExtensionTest {

    @Test
    fun shouldMultipleValuesAssertionSuccessful() {
        Observable.just(2, 3, 4).test().assertValuesThat { it > 0 }
    }

    @Test(expected = AssertionError::class)
    fun shouldMultipleValuesAssertionFailed() {
        Observable.just(1, 3, 4).test().assertValuesThat { it == 0 }
    }

    @Test
    fun shouldFirstValueAssertionSuccessful() {
        Observable.just(4, -1).test().assertValueThat { it > 0 }
    }

    @Test(expected = AssertionError::class)
    fun shouldFirstValueAssertionFailed() {
        Observable.just(1, -1, -10).test().assertValueThat { it == 0 }
    }
}
package com.elpassion.android.commons.rxjavatest

import org.junit.Test
import rx.Observable


class TestSubscriberExtensionTest {

    @Test
    fun shouldAssertionSuccessful() {
        Observable.just(2, 3, 4).test().assertValuesThat { this > 0 }
    }

    @Test(expected = AssertionError::class)
    fun shouldAssertionFailed() {
        Observable.just(1).test().assertValuesThat { this == 0 }
    }
}
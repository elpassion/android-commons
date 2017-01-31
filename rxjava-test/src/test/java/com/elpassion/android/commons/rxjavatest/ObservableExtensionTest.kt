package com.elpassion.android.commons.rxjavatest

import org.junit.Assert.assertFalse
import org.junit.Test
import rx.Observable


class ObservableExtensionTest {

    @Test
    fun shouldSubscribeAfterTestMethod() {
        val subscriber = Observable.never<Unit>().test()

        assertFalse(subscriber.isUnsubscribed)
    }

    @Test
    fun shouldAssertionSuccessful() {
        Observable.just(2).test().assertValue(2)
    }

    @Test(expected = AssertionError::class)
    fun shouldAssertionFailed() {
        Observable.just(2).test().assertValue(-1)
    }

    @Test(expected = AssertionError::class)
    fun shouldFailAssertionErrorChain() {
        val error = RuntimeException()
        Observable.error<Int>(error).test {
            assertError(error)
            assertNoErrors()
        }
    }
}
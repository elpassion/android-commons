package com.elpassion.android.commons.rxjavatest

import org.junit.Assert.assertFalse
import org.junit.Test
import rx.Completable


class CompletableExtensionTest {

    @Test
    fun shouldSubscribeAfterTestMethod() {
        val subscriber = Completable.never().test()

        assertFalse(subscriber.isUnsubscribed)
    }

    @Test
    fun shouldAssertionSuccessful() {
        Completable.complete().test().assertCompleted()
    }

    @Test(expected = AssertionError::class)
    fun shouldAssertionFailed() {
        Completable.never().test().assertCompleted()
    }

    @Test(expected = AssertionError::class)
    fun shouldFailAssertionErrorChain() {
        val error = RuntimeException()
        Completable.error(error).test {
            assertError(error)
            assertNoErrors()
        }
    }
}
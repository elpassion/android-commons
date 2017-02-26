package com.elpassion.android.commons.rxjavatest

import org.junit.Test
import rx.Completable

class CompletableExtensionTest {

    @Test
    fun shouldTestSubscriberAssertCompleted() {
        Completable.complete().test().assertCompleted()
    }

    @Test(expected = AssertionError::class)
    fun shouldTestSubscriberAssertFailed() {
        Completable.never().test().assertCompleted()
    }

    @Test(expected = AssertionError::class)
    fun shouldFailAssertionErrorChain() {
        Completable.error(RuntimeException()).test {
            assertNoErrors()
        }
    }

    @Test
    fun shouldNotFailAssertionErrorChain() {
        val error = RuntimeException()
        Completable.error(error).test {
            assertError(error)
        }
    }
}
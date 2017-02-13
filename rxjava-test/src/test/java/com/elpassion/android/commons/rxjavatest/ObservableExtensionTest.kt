package com.elpassion.android.commons.rxjavatest

import org.junit.Test
import rx.Observable

class ObservableExtensionTest {

    @Test
    fun shouldTestSubscriberAssertValue() {
        Observable.just(2).test().assertValue(2)
    }

    @Test(expected = AssertionError::class)
    fun shouldTestSubscriberThrowAssertionError() {
        Observable.just(2).test().assertValue(-1)
    }

    @Test
    fun shouldTestSubscriberAssertValueOnBlock() {
        Observable.just(2).test { assertValue(2) }
    }

    @Test(expected = AssertionError::class)
    fun shouldTestSubscriberThrowAssertionErrorOnBlock() {
        Observable.just(2).test { assertValue(3) }
    }

    @Test(expected = AssertionError::class)
    fun shouldFailAssertionErrorChain() {
        Observable.error<Unit>(RuntimeException()).test {
            assertNoErrors()
        }
    }

    @Test
    fun shouldNotFailAssertionErrorChain() {
        val error = RuntimeException()
        Observable.error<Unit>(error).test {
            assertError(error)
        }
    }
}
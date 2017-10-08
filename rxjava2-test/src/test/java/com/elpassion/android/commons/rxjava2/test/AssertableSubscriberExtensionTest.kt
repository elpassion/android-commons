package com.elpassion.android.commons.rxjava2.test

import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Assert
import org.junit.Test

class AssertableSubscriberExtensionTest {

    @Test
    fun shouldMultipleValuesAssertionSuccessful() {
        Observable.just(2, 3, 4).test().assertValuesThat { it > 0 }
    }

    @Test(expected = AssertionError::class)
    fun shouldMultipleValuesAssertionFailed() {
        Observable.just(1, 3, 4).test().assertValuesThat { it == 0 }
    }

    @Test(expected = AssertionError::class)
    fun shouldReallyMultipleValuesAssertionFailed() {
        Observable.just(1, -1, 4).test().assertValuesThat { it > 0 }
    }

    @Test
    fun shouldFirstValueAssertionSuccessful() {
        Observable.just(4, -1).test().assertValueThat { it > 0 }
    }

    @Test(expected = AssertionError::class)
    fun shouldFirstValueAssertionFailed() {
        Observable.just(1, -1, -10).test().assertValueThat { it == 0 }
    }

    @Test
    fun shouldCompareLastElement() {
        Observable.just(1, 2).test().assertLastValue(2)
    }

    @Test(expected = AssertionError::class)
    fun shouldFailWhenComparingLastElement() {
        Observable.just(2, 3).test().assertLastValue(2)
    }

    @Test
    fun shouldCompareLastElementWithPassedValue() {
        Observable.just(2, 3).test().assertLastValue(3)
    }

    @Test
    fun shouldTestLastElement() {
        Observable.just(1, 3).test().assertLastValueThat { this > 2 }
    }

    @Test(expected = AssertionError::class)
    fun shouldFailWhenTestingLastElement() {
        Observable.just(3, 1).test().assertLastValueThat { this > 2 }
    }

    @Test
    fun shouldTestLastElementWithPassedPredicate() {
        Observable.just(3, 1).test().assertLastValueThat { this < 2 }
    }
}
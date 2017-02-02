package com.elpassion.android.commons.rxjavatest

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Assert.assertFalse
import org.junit.Test
import rx.Observable


class ObservableExtensionTest {

    @Test
            //TODO: CR: Misleading test name
    fun shouldSubscribeAfterTestMethod() {
        val subscriber = Observable.never<Unit>().test()

        //TODO: CR: Assertion doesn't match test name
        assertFalse(subscriber.isUnsubscribed)
    }

    @Test
            //TODO: CR: Emphasise tested method behavior in test name
    fun shouldAssertionSuccessful() {
        Observable.just(2).test().assertValue(2)
    }

    @Test(expected = AssertionError::class)
            //TODO: CR: Emphasise tested method behavior in test name
    fun shouldAssertionFailed() {
        Observable.just(2).test().assertValue(-1)
    }

    @Test(expected = AssertionError::class)
    fun shouldFailAssertionErrorChain() {
        val error = RuntimeException()
        //TODO: CR: Add test for success case
        Observable.error<Int>(error).test {
            //TODO: CR: Remove unnecessary line
            assertError(error)
            assertNoErrors()
        }
    }

    @Test
    fun shouldReturnObservableNeverWhenThenNeverIsUsed() {
        val mock = mock<Function0<Observable<Unit>>>()
        whenever(mock.invoke()).thenNever()
        mock.invoke().test().apply {
            assertNoValues()
            assertNoErrors()
            assertNotCompleted()
        }
    }
}
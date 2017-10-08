package com.elpassion.android.commons.rxjavatest

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Test
import rx.Observable
import rx.observers.AssertableSubscriber

class StubbingObservableExtensionTest {

    @Test
    fun shouldReturnObservableNeverWhenThenNeverIsUsed() {
        val mock = mock<Function0<Observable<Unit>>>()
        whenever(mock.invoke()).thenNever()
        mock.invoke().test().assertObservableNever()
    }

    @Test
    fun shouldReturnObservableNeverWhenDoReturnNeverIsUsed() {
        val mock = mock<Function0<Observable<Unit>>>()
        whenever(mock.invoke()).doReturnNever()
        mock.invoke().test().assertObservableNever()
    }

    @Test
    fun shouldReturnObservableJustWhenThenJustIsUsed() {
        val mock = mock<Function0<Observable<Unit>>>()
        whenever(mock.invoke()).thenJust(Unit)
        mock.invoke().test().assertObservableJust(Unit)
    }

    @Test
    fun shouldReturnObservableJustWhenThenDoReturnJustIsUsed() {
        val mock = mock<Function0<Observable<Unit>>>()
        whenever(mock.invoke()).doReturnJust(Unit)
        mock.invoke().test().assertObservableJust(Unit)
    }

    @Test
    fun shouldReturnObservableJustOnListWhenThenJustIsUsed() {
        val mock = mock<Function0<Observable<List<Unit>>>>()
        whenever(mock.invoke()).thenJust(Unit, Unit)
        mock.invoke().test().assertObservableJust(Unit, Unit)
    }

    @Test
    fun shouldReturnObservableJustOnListWhenDoReturnJustIsUsed() {
        val mock = mock<Function0<Observable<List<Unit>>>>()
        whenever(mock.invoke()).doReturnJust(Unit, Unit)
        mock.invoke().test().assertObservableJust(Unit, Unit)
    }

    @Test
    fun shouldReturnObservableErrorWhenThenErrorIsUsed() {
        val mock = mock<Function0<Observable<Unit>>>()
        val expectedError = RuntimeException()
        whenever(mock.invoke()).thenError(expectedError)
        mock.invoke().test().assertObservableError(expectedError)
    }

    @Test
    fun shouldReturnObservableErrorWhenDoReturnErrorIsUsed() {
        val mock = mock<Function0<Observable<Unit>>>()
        val expectedError = RuntimeException()
        whenever(mock.invoke()).doReturnError(expectedError)
        mock.invoke().test().assertObservableError(expectedError)
    }


    private fun <T> AssertableSubscriber<T>.assertObservableError(expectedError: RuntimeException) {
        assertError(expectedError)
        assertNoValues()
        assertNotCompleted()
        assertTerminalEvent()
    }

    private fun <T> AssertableSubscriber<T>.assertObservableNever() {
        assertNoValues()
        assertNoErrors()
        assertNotCompleted()
    }

    private fun <T> AssertableSubscriber<T>.assertObservableJust(value: T) {
        assertValue(value)
        assertNoErrors()
        assertCompleted()
    }

    private fun <T> AssertableSubscriber<List<T>>.assertObservableJust(vararg values: T) {
        assertValues(values.toList())
        assertNoErrors()
        assertCompleted()
    }
}
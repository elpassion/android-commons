package com.elpassion.android.commons.rxjavatest

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Test

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


    private fun <T> TestObserver<T>.assertObservableError(expectedError: RuntimeException) {
        assertError(expectedError)
        assertNoValues()
        assertNotComplete()
        assertTerminated()
    }

    private fun <T> TestObserver<T>.assertObservableNever() {
        assertNoValues()
        assertNoErrors()
        assertNotComplete()
    }

    private fun <T> TestObserver<T>.assertObservableJust(value: T) {
        assertValue(value)
        assertNoErrors()
        assertComplete()
    }

    private fun <T> TestObserver<List<T>>.assertObservableJust(vararg values: T) {
        assertValues(values.toList())
        assertNoErrors()
        assertComplete()
    }
}
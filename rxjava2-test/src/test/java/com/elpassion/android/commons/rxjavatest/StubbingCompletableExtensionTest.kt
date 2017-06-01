package com.elpassion.android.commons.rxjavatest

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.observers.TestObserver
import org.junit.Test

class StubbingCompletableExtensionTest {

    @Test
    fun shouldReturnCompletableNeverWhenThenNeverIsUsed() {
        val mock = mock<Function0<Completable>>()
        whenever(mock.invoke()).thenNever()
        mock.invoke().test().assertCompletableNever()
    }

    @Test
    fun shouldReturnCompletableNeverWhenDoReturnNeverIsUsed() {
        val mock = mock<Function0<Completable>>()
        whenever(mock.invoke()).doReturnNever()
        mock.invoke().test().assertCompletableNever()
    }

    @Test
    fun shouldReturnCompletableJustWhenThenJustIsUsed() {
        val mock = mock<Function0<Completable>>()
        whenever(mock.invoke()).thenComplete()
        mock.invoke().test().assertCompletableComplete()
    }

    @Test
    fun shouldReturnCompletableJustWhenThenDoReturnJustIsUsed() {
        val mock = mock<Function0<Completable>>()
        whenever(mock.invoke()).doReturnComplete()
        mock.invoke().test().assertCompletableComplete()
    }

    @Test
    fun shouldReturnCompletableErrorWhenThenErrorIsUsed() {
        val mock = mock<Function0<Completable>>()
        val expectedError = RuntimeException()
        whenever(mock.invoke()).thenError(expectedError)
        mock.invoke().test().assertCompletableError(expectedError)
    }

    @Test
    fun shouldReturnCompletableErrorWhenDoReturnErrorIsUsed() {
        val mock = mock<Function0<Completable>>()
        val expectedError = RuntimeException()
        whenever(mock.invoke()).doReturnError(expectedError)
        mock.invoke().test().assertCompletableError(expectedError)
    }

    private fun <T> TestObserver<T>.assertCompletableError(expectedError: RuntimeException) {
        assertNoValues()
        assertError(expectedError)
        assertNotComplete()
        assertTerminated()
    }

    private fun <T> TestObserver<T>.assertCompletableNever() {
        assertNoValues()
        assertNoErrors()
        assertNotComplete()
    }

    private fun <T> TestObserver<T>.assertCompletableComplete() {
        assertNoValues()
        assertNoErrors()
        assertComplete()
    }
}
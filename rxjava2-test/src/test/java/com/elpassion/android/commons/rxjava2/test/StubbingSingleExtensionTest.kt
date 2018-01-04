package com.elpassion.android.commons.rxjava2.test

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Test

class StubbingSingleExtensionTest {

    @Test
    fun shouldReturnSingleNeverWhenThenNeverIsUsed() {
        val mock = mock<Function0<Single<Unit>>>()
        whenever(mock.invoke()).thenNever()
        mock.invoke().test().assertSingleNever()
    }

    @Test
    fun shouldReturnSingleNeverWhenDoReturnNeverIsUsed() {
        val mock = mock<Function0<Single<Unit>>>()
        whenever(mock.invoke()).doReturnNever()
        mock.invoke().test().assertSingleNever()
    }

    @Test
    fun shouldReturnSingleJustWhenThenJustIsUsed() {
        val mock = mock<Function0<Single<Unit>>>()
        whenever(mock.invoke()).thenJust(Unit)
        mock.invoke().test().assertSingleJust(Unit)
    }

    @Test
    fun shouldReturnSingleJustWhenThenDoReturnJustIsUsed() {
        val mock = mock<Function0<Single<Unit>>>()
        whenever(mock.invoke()).doReturnJust(Unit)
        mock.invoke().test().assertSingleJust(Unit)
    }

    @Test
    fun shouldReturnSingleErrorWhenThenErrorIsUsed() {
        val api = mock<Function0<Single<Unit>>>()
        val error = RuntimeException()
        // tag::rxjava2-then-error[]
        whenever(api.invoke()).thenError(error)
        // end::rxjava2-then-error[]
        api.invoke().test().assertSingleError(error)
    }

    @Test
    fun shouldReturnSingleErrorWhenDoReturnErrorIsUsed() {
        val mock = mock<Function0<Single<Unit>>>()
        val expectedError = RuntimeException()
        whenever(mock.invoke()).doReturnError(expectedError)
        mock.invoke().test().assertSingleError(expectedError)
    }

    private fun <T> TestObserver<T>.assertSingleError(expectedError: RuntimeException) {
        assertError(expectedError)
        assertNoValues()
        assertNotComplete()
        assertTerminated()
    }

    private fun <T> TestObserver<T>.assertSingleNever() {
        assertNoValues()
        assertNoErrors()
        assertNotComplete()
    }

    private fun <T> TestObserver<T>.assertSingleJust(value: T) {
        assertValue(value)
        assertNoErrors()
        assertComplete()
    }
}
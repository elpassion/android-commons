package com.elpassion.android.commons.rxjavatest

import com.nhaarman.mockito_kotlin.doReturn
import org.mockito.stubbing.OngoingStubbing
import rx.Observable
import rx.observers.TestSubscriber

fun <T> OngoingStubbing<Observable<T>>.thenNever(): OngoingStubbing<Observable<T>> = thenReturn(Observable.never())

fun <T> OngoingStubbing<Observable<T>>.thenError(exception: Exception): OngoingStubbing<Observable<T>> = thenReturn(Observable.error(exception))

fun <T> OngoingStubbing<Observable<T>>.thenJust(response: T): OngoingStubbing<Observable<T>> = thenReturn(Observable.just(response))

fun <T> OngoingStubbing<Observable<T>>.doReturnJust(value: T) = doReturn(Observable.just(value))

fun <T> OngoingStubbing<Observable<T>>.doReturnNever() = doReturn(Observable.never())

fun <T> OngoingStubbing<Observable<T>>.doReturnError(exception: Exception) = doReturn(Observable.error(exception))

fun <T> Observable<T>.test() = TestSubscriber<T>().apply { subscribe(this) }

fun <T> Observable<T>.test(assertion: TestSubscriber<T>.() -> Unit) = test().assertion()
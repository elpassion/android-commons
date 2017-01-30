package com.elpassion.android.commons.rxjavatest

import org.mockito.stubbing.OngoingStubbing
import rx.Observable
import rx.observers.TestSubscriber

fun <T> OngoingStubbing<Observable<T>>.thenNeverending(): OngoingStubbing<Observable<T>> = thenReturn(Observable.never())

fun <T> OngoingStubbing<Observable<T>>.thenError(exception: Exception): OngoingStubbing<Observable<T>> = thenReturn(Observable.error(exception))

fun <T> OngoingStubbing<Observable<T>>.thenJust(response: T): OngoingStubbing<Observable<T>> = thenReturn(Observable.just(response))

fun <T> Observable<T>.test() = TestSubscriber<T>().apply { subscribe(this) }

fun <T> Observable<T>.test(assertion: TestSubscriber<T>.() -> Unit) = test().assertion()
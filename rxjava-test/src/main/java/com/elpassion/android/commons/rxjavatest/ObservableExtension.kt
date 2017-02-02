package com.elpassion.android.commons.rxjavatest

import org.mockito.stubbing.OngoingStubbing
import rx.Observable
import rx.observers.TestSubscriber

fun <T> OngoingStubbing<Observable<T>>.thenNever(): OngoingStubbing<Observable<T>> = thenReturn(Observable.never())

fun <T> Observable<T>.test() = TestSubscriber<T>().apply { subscribe(this) }

fun <T> Observable<T>.test(assertion: TestSubscriber<T>.() -> Unit) = test().assertion()
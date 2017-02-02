package com.elpassion.android.commons.rxjavatest

import org.mockito.stubbing.OngoingStubbing
import rx.Observable
import rx.observers.TestSubscriber

//TODO: CR: Restore methods removed in 2e64b5601019633a6a11e750c6e222c489356c76 in TDD
fun <T> OngoingStubbing<Observable<T>>.thenNever(): OngoingStubbing<Observable<T>> = thenReturn(Observable.never())

fun <T> Observable<T>.test() = TestSubscriber<T>().apply { subscribe(this) }

fun <T> Observable<T>.test(assertion: TestSubscriber<T>.() -> Unit) = test().assertion()
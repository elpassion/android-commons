package com.elpassion.android.commons.rxjavatest

import rx.Observable
import rx.observers.TestSubscriber

fun <T> Observable<T>.test() = TestSubscriber<T>().apply { subscribe(this) }

fun <T> Observable<T>.test(assertion: TestSubscriber<T>.() -> Unit) = test().assertion()
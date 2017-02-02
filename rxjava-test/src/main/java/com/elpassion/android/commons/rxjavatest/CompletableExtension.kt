package com.elpassion.android.commons.rxjavatest

import rx.Completable
import rx.observers.TestSubscriber

fun Completable.test() = TestSubscriber<Unit>().apply { subscribe(this) }

fun Completable.test(assertion: TestSubscriber<Unit>.() -> Unit) = test().assertion()
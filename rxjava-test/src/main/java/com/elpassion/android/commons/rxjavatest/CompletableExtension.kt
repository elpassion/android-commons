package com.elpassion.android.commons.rxjavatest

import rx.Completable
import rx.observers.TestSubscriber

//TODO: CR: Restore methods removed in 2e64b5601019633a6a11e750c6e222c489356c76 in TDD
fun Completable.test() = TestSubscriber<Unit>().apply { subscribe(this) }

fun Completable.test(assertion: TestSubscriber<Unit>.() -> Unit) = test().assertion()
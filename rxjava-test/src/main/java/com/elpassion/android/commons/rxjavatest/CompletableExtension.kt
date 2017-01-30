package com.elpassion.android.commons.rxjavatest

import org.mockito.stubbing.OngoingStubbing
import rx.Completable
import rx.observers.TestSubscriber

fun OngoingStubbing<Completable>.thenNeverending() = thenReturn(Completable.never())

fun OngoingStubbing<Completable>.thenError(exception: Exception) = thenReturn(Completable.error(exception))

fun OngoingStubbing<Completable>.thenComplete() = thenReturn(Completable.complete())

fun Completable.test(): TestSubscriber<Unit> {
    val subscriber = TestSubscriber<Unit>()
    subscribe(subscriber)
    return subscriber
}

fun Completable.test(assertion: TestSubscriber<Unit>.() -> Unit) {
    val subscriber = TestSubscriber<Unit>()
    subscribe(subscriber)
    subscriber.assertion()
}
package com.elpassion.android.commons.rxjavatest

import rx.Completable
import rx.observers.TestSubscriber
import com.nhaarman.mockito_kotlin.doReturn
import org.mockito.stubbing.OngoingStubbing

fun OngoingStubbing<Completable>.thenNever(): OngoingStubbing<Completable> = thenReturn(Completable.never())

fun OngoingStubbing<Completable>.thenError(exception: Exception): OngoingStubbing<Completable> = thenReturn(Completable.error(exception))

fun OngoingStubbing<Completable>.thenComplete() = thenReturn(Completable.complete())

fun OngoingStubbing<Completable>.doReturnComplete() = doReturn(Completable.complete())

fun OngoingStubbing<Completable>.doReturnNever() = doReturn(Completable.never())

fun OngoingStubbing<Completable>.doReturnError(exception: Exception) = doReturn(Completable.error(exception))

fun Completable.test() = TestSubscriber<Unit>().apply { subscribe(this) }

fun Completable.test(assertion: TestSubscriber<Unit>.() -> Unit) = test().assertion()
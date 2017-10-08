package com.elpassion.android.commons.rxjavatest

import com.nhaarman.mockito_kotlin.doReturn
import org.mockito.stubbing.OngoingStubbing
import rx.Completable

fun OngoingStubbing<Completable>.thenNever(): OngoingStubbing<Completable> = thenReturn(Completable.never())

fun OngoingStubbing<Completable>.thenError(exception: Exception): OngoingStubbing<Completable> = thenReturn(Completable.error(exception))

fun OngoingStubbing<Completable>.thenComplete(): OngoingStubbing<Completable> = thenReturn(Completable.complete())

fun OngoingStubbing<Completable>.doReturnComplete() = doReturn(Completable.complete())

fun OngoingStubbing<Completable>.doReturnNever() = doReturn(Completable.never())

fun OngoingStubbing<Completable>.doReturnError(exception: Exception) = doReturn(Completable.error(exception))
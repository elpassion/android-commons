package com.elpassion.android.commons.rxjavatest

import org.mockito.stubbing.OngoingStubbing
import rx.Completable

fun OngoingStubbing<Completable>.thenNeverending() = thenReturn(Completable.never())

fun OngoingStubbing<Completable>.thenError(exception: Exception) = thenReturn(Completable.error(exception))

fun OngoingStubbing<Completable>.thenComplete() = thenReturn(Completable.complete())
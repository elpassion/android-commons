package com.elpassion.android.commons.rxjava2.test

import com.nhaarman.mockito_kotlin.doReturn
import io.reactivex.Single
import org.mockito.stubbing.OngoingStubbing

fun <T> OngoingStubbing<Single<T>>.thenNever(): OngoingStubbing<Single<T>> = thenReturn(Single.never())

fun <T> OngoingStubbing<Single<T>>.thenJust(value: T): OngoingStubbing<Single<T>> = thenReturn(Single.just(value))

fun <T> OngoingStubbing<Single<T>>.thenError(exception: Exception = RuntimeException()): OngoingStubbing<Single<T>> = thenReturn(Single.error(exception))

fun <T> OngoingStubbing<Single<T>>.doReturnJust(value: T) = doReturn(Single.just(value))

fun <T> OngoingStubbing<Single<T>>.doReturnNever() = doReturn(Single.never())

fun <T> OngoingStubbing<Single<T>>.doReturnError(exception: Exception = RuntimeException()) = doReturn(Single.error(exception))
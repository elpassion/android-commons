package com.elpassion.android.commons.rxjava2.test

import com.nhaarman.mockito_kotlin.doReturn
import io.reactivex.Observable
import org.mockito.stubbing.OngoingStubbing

fun <T> OngoingStubbing<Observable<T>>.thenNever(): OngoingStubbing<Observable<T>> = thenReturn(Observable.never())

fun <T> OngoingStubbing<Observable<T>>.thenJust(value: T): OngoingStubbing<Observable<T>> = thenReturn(Observable.just(value))

fun <T> OngoingStubbing<Observable<List<T>>>.thenJust(vararg values: T): OngoingStubbing<Observable<List<T>>> = thenReturn(Observable.just(values.toList()))

fun <T> OngoingStubbing<Observable<T>>.thenError(exception: Exception = RuntimeException()): OngoingStubbing<Observable<T>> = thenReturn(Observable.error(exception))

fun <T> OngoingStubbing<Observable<T>>.doReturnJust(value: T) = doReturn(Observable.just(value))

fun <T> OngoingStubbing<Observable<List<T>>>.doReturnJust(vararg values: T) = doReturn(Observable.just(values.toList()))

fun <T> OngoingStubbing<Observable<T>>.doReturnNever() = doReturn(Observable.never())

fun <T> OngoingStubbing<Observable<T>>.doReturnError(exception: Exception = RuntimeException()) = doReturn(Observable.error(exception))
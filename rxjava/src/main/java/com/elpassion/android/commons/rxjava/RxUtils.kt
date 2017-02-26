package com.elpassion.android.commons.rxjava

import rx.Observable
import rx.Subscription
import rx.subscriptions.CompositeSubscription

fun Subscription.addTo(subscription: CompositeSubscription) = subscription.add(this)

fun <T> Observable<T>.catchOnError(handleOnError: (throwable: Throwable) -> Unit): Observable<T> = this
        .onErrorResumeNext {
            handleOnError(it)
            Observable.empty()
        }

fun <T> Observable<T>.changes(init: T) = scan(init to init) { acc, cur -> acc.second to cur }.skip(1)

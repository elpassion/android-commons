package com.elpassion.android.commons.rxjavatest

import org.junit.Rule
import org.junit.Test
import rx.Observable
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.concurrent.Executors


class RxSchedulersRuleTest {

    @get:Rule val rule = RxSchedulersRule()
    val mainThread: Thread = Thread.currentThread()

    @Test
    fun shouldStandardSubscriberCallOnMainThread() {
        threadObservable()
                .test()
                .assertValue(mainThread)
    }

    @Test
    fun shouldSubscribeOnIoCallOnMainThread() {
        threadObservableObserveOnScheduler(Schedulers.io())
                .test()
                .assertValue(mainThread)
    }

    @Test
    fun shouldObserveOnIoCallOnMainThread() {
        threadObservableObserveOnScheduler(Schedulers.io())
                .test()
                .assertValue(mainThread)
    }

    @Test
    fun shouldSubscribeOnComputeCallOnMainThread() {
        threadObservable()
                .subscribeOn(Schedulers.computation())
                .test()
                .assertValue(mainThread)
    }

    @Test
    fun shouldObserveOnComputeCallOnMainThread() {
        threadObservableObserveOnScheduler(Schedulers.computation())
                .test()
                .assertValue(mainThread)
    }

    @Test
    fun shouldSubscribeOnAndroidLooperCallOnMainThread() {
        threadObservable()
                .subscribeOn(AndroidSchedulers.mainThread())
                .test()
                .assertValue(mainThread)
    }

    @Test
    fun shouldObserveOnAndroidLooperCallOnMainThread() {
        threadObservableObserveOnScheduler(AndroidSchedulers.mainThread())
                .test()
                .assertValue(mainThread)
    }

    @Test
    fun shouldSubscribeOnCustomSchedulersNotCalledOnMainThread() {
        val scheduler = Schedulers.from(Executors.newSingleThreadExecutor())
        threadObservable()
                .subscribeOn(scheduler)
                .test()
                .assertValueThat { it != mainThread }
    }

    @Test
    fun shouldObserveOnCustomSchedulersNotCalledOnMainThread() {
        val scheduler = Schedulers.from(Executors.newSingleThreadExecutor())
        threadObservableObserveOnScheduler(scheduler)
                .test()
                .assertValueThat { it != mainThread }
    }

    private fun threadObservableObserveOnScheduler(scheduler: Scheduler) = Observable.just(Unit)
            .observeOn(scheduler)
            .flatMap { threadObservable() }

    private fun threadObservable(): Observable<Thread> =
            Observable.just(Unit).map { Thread.currentThread() }
}
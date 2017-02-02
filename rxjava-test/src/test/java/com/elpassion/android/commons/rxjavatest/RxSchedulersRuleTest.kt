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
        currentThreadObservable()
                .test()
                .assertValue(mainThread)
    }

    @Test
    fun shouldSubscribeOnIoCallOnMainThread() {
        observableObserveOnScheduler(Schedulers.io())
                .test()
                .assertValue(mainThread)
    }

    @Test
    fun shouldObserveOnIoCallOnMainThread() {
        observableObserveOnScheduler(Schedulers.io())
                .test()
                .assertValue(mainThread)
    }

    @Test
    fun shouldSubscribeOnComputeCallOnMainThread() {
        currentThreadObservable()
                .subscribeOn(Schedulers.computation())
                .test()
                .assertValue(mainThread)
    }

    @Test
    fun shouldObserveOnComputeCallOnMainThread() {
        observableObserveOnScheduler(Schedulers.computation())
                .test()
                .assertValue(mainThread)
    }

    @Test
    fun shouldSubscribeOnAndroidLooperCallOnMainThread() {
        currentThreadObservable()
                .subscribeOn(AndroidSchedulers.mainThread())
                .test()
                .assertValue(mainThread)
    }

    @Test
    fun shouldObserveOnAndroidLooperCallOnMainThread() {
        observableObserveOnScheduler(AndroidSchedulers.mainThread())
                .test()
                .assertValue(mainThread)
    }

    @Test
    fun shouldSubscribeOnCustomSchedulersNotCalledOnMainThread() {
        val scheduler = Schedulers.from(Executors.newSingleThreadExecutor())
        currentThreadObservable()
                .subscribeOn(scheduler)
                .test()
                .assertValueThat { it != mainThread }
    }

    @Test
    fun shouldObserveOnCustomSchedulersNotCalledOnMainThread() {
        val scheduler = Schedulers.from(Executors.newSingleThreadExecutor())
        observableObserveOnScheduler(scheduler)
                .test()
                .assertValueThat { it != mainThread }
    }

    private fun observableObserveOnScheduler(scheduler: Scheduler) = Observable.just(Unit)
            .observeOn(scheduler)
            .map { Thread.currentThread() }

    private fun currentThreadObservable(): Observable<Thread> = Observable.defer { Observable.just(Thread.currentThread()) }
}
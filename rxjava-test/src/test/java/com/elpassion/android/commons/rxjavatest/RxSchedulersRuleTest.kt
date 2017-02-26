package com.elpassion.android.commons.rxjavatest

import org.junit.Rule
import org.junit.Test
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class RxSchedulersRuleTest {

    @Rule @JvmField
    val rule = RxSchedulersRule()
    val parentThread: Thread = Thread.currentThread()
    private val observable = Observable.just(Unit)

    @Test
    fun shouldNotChangeThread() {
        observable
                .assertThread(parentThread)
    }

    @Test
    fun shouldNotChangeThreadWhenSubscribedOnIoThread() {
        observable
                .subscribeOn(Schedulers.io())
                .assertThread(parentThread)
    }

    @Test
    fun shouldNotChangeThreadWhenObservedOnIoThread() {
        observable
                .observeOn(Schedulers.io())
                .assertThread(parentThread)
    }

    @Test
    fun shouldNotChangeThreadWhenSubscribedOnComputationThread() {
        observable
                .subscribeOn(Schedulers.computation())
                .assertThread(parentThread)
    }

    @Test
    fun shouldNotChangeThreadWhenObservedOnComputationThread() {
        observable
                .observeOn(Schedulers.computation())
                .assertThread(parentThread)
    }

    @Test
    fun shouldNotChangeThreadWhenSubscribedOnAndroidMainThread() {
        observable
                .subscribeOn(AndroidSchedulers.mainThread())
                .assertThread(parentThread)
    }

    @Test
    fun shouldNotChangeThreadWhenObservedOnAndroidMainThread() {
        observable
                .observeOn(AndroidSchedulers.mainThread())
                .assertThread(parentThread)
    }

    @Test
    fun shouldChangeThreadWhenSubscribedOnNewThread() {
        observable
                .subscribeOn(Schedulers.newThread())
                .assertNotThread(parentThread)
    }

    @Test
    fun shouldChangeThreadWhenObservedOnNewThread() {
        observable
                .observeOn(Schedulers.newThread())
                .assertNotThread(parentThread)
    }

    private fun Observable<Unit>.assertThread(thread: Thread) = this
            .map { Thread.currentThread() }
            .test {
                assertValue(thread)
            }

    private fun Observable<Unit>.assertNotThread(thread: Thread) = this
            .map { Thread.currentThread() }
            .test {
                awaitValueCount(1, 1, TimeUnit.SECONDS)
                assertValueThat { it != thread }
            }
}


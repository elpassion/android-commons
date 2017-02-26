package com.elpassion.android.commons.rxjava

import org.junit.Test
import rx.observers.TestSubscriber
import rx.subjects.PublishSubject

class ChangesTest {

    private val subject = PublishSubject.create<Int>()
    private val subscriber = TestSubscriber<Pair<Int, Int>>()

    @Test
    fun shouldReturnCorrectPair() {

        subject
                .changes(0)
                .subscribe(subscriber)

        subject.onNext(1)

        subscriber.assertValues(0 to 1)
    }

    @Test
    fun shouldReturnCorrectPairs() {

        subject
                .changes(0)
                .subscribe(subscriber)

        subject.onNext(1)
        subject.onNext(2)
        subject.onNext(3)
        subject.onNext(4)

        subscriber.assertValues(
                0 to 1,
                1 to 2,
                2 to 3,
                3 to 4
        )
    }

    @Test // not actually needed but fun :)
    fun shouldReturnCorrectPairsOfPairs() {

        val subscriber = TestSubscriber<Pair<Pair<Int, Int>, Pair<Int, Int>>>()

        subject
                .changes(333)
                .changes(666 to 667)
                .subscribe(subscriber)

        subject.onNext(999)
        subject.onNext(998)
        subject.onNext(997)
        subject.onNext(996)

        subscriber.assertValues(
                (666 to 667) to (333 to 999),
                (333 to 999) to (999 to 998),
                (999 to 998) to (998 to 997),
                (998 to 997) to (997 to 996)
        )
    }
}
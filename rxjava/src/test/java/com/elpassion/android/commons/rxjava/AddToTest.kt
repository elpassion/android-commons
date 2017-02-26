package com.elpassion.android.commons.rxjava

import org.junit.Test
import rx.subjects.PublishSubject
import rx.subscriptions.CompositeSubscription


class AddToTest {
    private val subject = PublishSubject.create<Int>()
    private val subscription = CompositeSubscription()

    @Test
    fun shouldAddSubscriptionToCompositeSubscription() {

        subject
                .subscribe()
                .addTo(subscription)

        assert(subscription.hasSubscriptions())
    }

    @Test
    fun shouldUnsubscribeViaCompositeSubscription() {

        subject
                .subscribe()
                .addTo(subscription)

        assert(subject.hasObservers())

        subscription.clear()

        assert(!subject.hasObservers())
    }
}
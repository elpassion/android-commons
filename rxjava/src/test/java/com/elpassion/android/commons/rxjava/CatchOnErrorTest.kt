package com.elpassion.android.commons.rxjava

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test
import rx.observers.TestSubscriber
import rx.subjects.PublishSubject


class CatchOnErrorTest {
    private val subject = PublishSubject.create<Int>()
    private val subscriber = TestSubscriber<Int>()
    private val handleException: (Exception) -> Unit = mock()

    @Test
    fun shouldCatchRxError() {

        subject
                .catchOnError {
                    when(it) {
                        is IllegalArgumentException -> handleException(it)
                        else -> throw it
                    }
                }
                .subscribe(subscriber)

        val exception = IllegalArgumentException()

        subject.onError(exception)

        verify(handleException).invoke(exception)
        subscriber.assertNoErrors()
    }

    @Test
    fun shouldNotCatchRxError() {

        subject
                .catchOnError {
                    when(it) {
                        is IllegalStateException -> handleException(it)
                        else -> throw it
                    }
                }
                .subscribe(subscriber)

        subject.onError(IllegalArgumentException())

        verify(handleException, times(0)).invoke(any())
        subscriber.assertError(IllegalArgumentException::class.java)
    }

    @Test
    fun shouldPassItemsThrough() {

        subject
                .catchOnError { }
                .subscribe(subscriber)

        subject.onNext(2)
        subject.onNext(3)

        subscriber.assertValues(2, 3)
        subscriber.assertNoErrors()
    }
}
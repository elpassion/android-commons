package com.elpassion.android.commons.rxjava

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.http.GET
import rx.Observable
import rx.Scheduler
import rx.observers.TestSubscriber
import rx.schedulers.Schedulers
import rx.schedulers.TestScheduler


class ObservingRxJavaCallAdapterFactoryTest {

    @Rule
    @JvmField
    val server = MockWebServer()

    private val observeOn = TestScheduler()
    private val testSubscriber = TestSubscriber<String>()

    @Before
    fun setUp() {
        server.enqueue(MockResponse().setBody("Hi"))
    }

    @Test
    fun shouldObserveOnProperScheduler() {
        val service = createRetrofit(observeOn).create(Service::class.java)
        service.call().subscribe(testSubscriber)

        testSubscriber.assertNoValues()
        observeOn.triggerActions()

        testSubscriber.apply {
            assertValueCount(1)
            assertCompleted()
        }
    }

    interface Service {
        @GET("/")
        fun call(): Observable<String>
    }

    private fun createRetrofit(observeOn: Scheduler) = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addConverterFactory(StringConverterFactory())
            .addCallAdapterFactory(ObservingRxJavaCallAdapterFactory(observeOn))
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build()
}
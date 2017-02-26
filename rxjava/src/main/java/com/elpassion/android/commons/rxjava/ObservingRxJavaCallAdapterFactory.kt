package com.elpassion.android.commons.rxjava

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import rx.Observable
import rx.Scheduler
import java.lang.reflect.Type

class ObservingRxJavaCallAdapterFactory(private val observeOn: Scheduler) : CallAdapter.Factory() {

    override fun get(returnType: Type, annotations: Array<out Annotation>, retrofit: Retrofit): CallAdapter<*>? {
        if (getRawType(returnType) != Observable::class.java) {
            return null
        }
        @Suppress("UNCHECKED_CAST")
        val delegate = retrofit.nextCallAdapter(this, returnType, annotations) as CallAdapter<Observable<*>>
        return object : CallAdapter<Observable<*>> {
            override fun <R : Any?> adapt(call: Call<R>?) = delegate.adapt(call).observeOn(observeOn)

            override fun responseType() = delegate.responseType()
        }
    }
}
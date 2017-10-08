package com.elpassion.android.commons.rxjava2

import io.reactivex.Observable
import org.junit.Test

class ObservableUtilsTest {

    @Test
    fun shouldFilterByIntType() {
        Observable.just(1, "a", 2, "b").type(Int::class).test().assertValues(1, 2)
    }

    @Test
    fun shouldFilterByStringType() {
        Observable.just(1, "a", 2, "b").type(String::class).test().assertValues("a", "b")
    }

    @Test
    fun shouldFilterByMultipleTypes() {
        Observable.just(1, "a", 2, "b", Unit).types(String::class, Int::class).test().assertValues(1, "a", 2, "b")
    }

    @Test
    fun shouldFilterByMultipleTypesWithUnit() {
        Observable.just(1, "a", 2, "b", Unit).types(Unit::class, Int::class).test().assertValues(1, 2, Unit)
    }
}
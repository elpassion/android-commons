# android-commons

Libraries written in Kotlin and used in most EL Passion Android projects.

[![Release](https://jitpack.io/v/elpassion/android-commons.svg)](https://jitpack.io/#elpassion/android-commons)

### Building with JitPack

```
repositories {
    maven { url "https://jitpack.io" }
}
```
Details: https://jitpack.io/#elpassion/android-commons

Overview
=================

In EL Passion we believe that every library should be as small as possible, and do one thing, but do
it well.

That's why we follow highly modularized approach.

Our library consists of following modules:
- [Espresso](#espresso) 
- [RxJava2-test](#rxJava2-test)
- [SharedPreferences](#sharedPreferences)
- [SharedPreferences-Moshi](#sharedPreferences-moshi)
- [SharedPreferences-Gson](#sharedPreferences-gson)
- [View](#view)
- [Pager](#pager)
- [Recycler](#recycler)

Espresso
=========

Espresso module contains useful stuff when writing tests using espresso framework.

For example instead of writing 
```java
    Espresso.onView(ViewMatchers.withId(R.id.button)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
```
Leveraging the kotlin expressiveness we can write:
```kotlin
    onId(R.id.button).isDisplayed()
```

#### Download:
```groovy
    androidTestImplementation "com.github.elpassion.android-commons:espresso:0.0.21"
```
[Back to overview](#overview)

RxJava2-test
=========

RxJava2-test module basically contains two things.
1. Set of extension methods useful when stubbing api
    
    For example instead of writing:
    ```
        whenever(api.makeRequest()).thenReturn(Completable.error(RuntimeException()))
    ```
    We can write:
    ```
        whenever(api.makeRequest()).thenError()
    ```
    There are corresponding methods for Observable, and Single classes as well.
2. Set of assertions for testObservers
    - `assertValueThat(predicate)` 
    - ` assertValuesThat(predicate)` 
    - `assertLastValueThat(predicate)` 


#### Download:
```groovy
    androidTestCompile "com.github.elpassion.android-commons:rxjava-test:0.0.21"
```
[Back to overview](#overview)

SharedPreferences
=========

This is a core module which provides an abstraction over android shared preferences.

```kotlin
interface SharedPreferenceRepository<T> {

    fun write(key: String, value: T?)

    fun read(key: String): T?

    fun contains(key: String): Boolean
}
```

To create an instance of it use the factory method:

```kotlin
createSharedPrefs(
            sharedPreferencesProvider = { PreferenceManager.getDefaultSharedPreferences(context) },
            jsonAdapter = jsonAdapter)
```

A JsonAdapter is a class which fulfills following contract:

```kotlin
interface JsonConverterAdapter<T> {
    fun toJson(t: T?): String

    fun fromJson(t: String): T?
}
```
As you see any class that can serialize objects to strings and later deserialize them will do.

We have created two adapters for the most commonly used serialization libraries:
1. [moshi-converter-adapter](#SharedPreferences-moshi-converter-adapter)
2. [gson-converter-adapter](#SharedPreferences-gson-converter-adapter)

There is also an extension method which binds any field into specific key from shared preferences
```kotlin
    var token: String? by sharedPreferences.asProperty(key = "authToken")
```
or you can use its non-nullable variant
```kotlin
    var token: String by sharedPreferences.asPropertyWithDefault(key = "authToken", default = "invalid")
```

#### Download:
```groovy
    implementation "com.github.elpassion.android-commons:shared-preferences:0.0.21"
```
[Back to overview](#overview)

SharedPreferences-Moshi
=========
It is a moshi adapter for our sharedPreferences library.

#### Download:
```groovy
    implementation "com.github.elpassion.android-commons:shared-preferences-moshi-converter-adapter:0.0.21"
```
[Back to overview](#overview)

SharedPreferences-Gson
=========
It is a gson adapter for our sharedPreferences library.

#### Download:
```groovy
    implementation "com.github.elpassion.android-commons:shared-preferences-gson-converter-adapter:0.0.21"
```
[Back to overview](#overview)

View
=========

#### Download:
```groovy
    implementation "com.github.elpassion.android-commons:view:0.0.21"
```
[Back to overview](#overview)

Pager
=========

#### Download:
```groovy
    implementation "com.github.elpassion.android-commons:pager:0.0.21"
```
[Back to overview](#overview)

Recycler
=========

#### Download:
```groovy
    implementation "com.github.elpassion.android-commons:recycler:0.0.21"
```
[Back to overview](#overview)
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
- [RxJava2-test](#rxjava2-test)
- [SharedPreferences](#sharedpreferences)
- [SharedPreferences-Moshi](#sharedpreferences-moshi)
- [SharedPreferences-Gson](#sharedpreferences-gson)
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
    androidTestImplementation "com.github.elpassion.android-commons:rxjava-test:0.0.21"
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

If you are reading very often we suggest you to wrap your sharedPreferences instance with CachingSharedPreferences wrapper.
It uses the same interface so it is transparent from the usage perspective. Keep in mind that in order to cache values and 
properly invalidate them, every interactions with sharedPreferences must now go through the same instance of the caching wrapper. 

#### Download:
```groovy
    implementation "com.github.elpassion.android-commons:shared-preferences:0.0.21"
```
[Back to overview](#overview)

SharedPreferences-Moshi
=========
It is a moshi adapter for our sharedPreferences library.

To create an instance of it use a factory function:
```kotlin
    moshiConverterAdapter(moshi = instanceOfMoshi)
```

#### Download:
```groovy
    implementation "com.github.elpassion.android-commons:shared-preferences-moshi-converter-adapter:0.0.21"
```
[Back to overview](#overview)

SharedPreferences-Gson
=========
It is a gson adapter for our sharedPreferences library.

To create an instance of it use a factory function:
```kotlin
    gsonConverterAdapter(gson = instanceOfGson)
```

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
Using recycler-view have never been easier.

If all of your views are going to be the same type you can just write
```kotlin
items = /* list of your items */
recyclerView.adapter = basicAdapterWithLayoutAndBinder(items, R.layout.my_item_layout) { holder, item ->
            holder.itemView.myTextView.text = item.name
        }
```
If you want to get benefits of using stableIds all you need to do is to make your item 
implement `WithStableId` interface and of course tell the adapter to use stable ids:
`adapter.setHasStableId(true)`

On the other hand if you need to have different views for different types of items you just need to write
```kotlin
items = /* list of your items */
recyclerView.adapter = basicAdapterWithConstructors(items) { position ->
            positionToLayoutMapping(position)
        }

private fun positionToLayoutMapping(position) = when (position) {
    isOdd() -> R.layout.github_item to ::SimpleUserViewHolder
    isEven() -> R.layout.other_github_item to ::OtherSimpleUserViewHolder
}
```
Where a view holder may look like this
```kotlin
class SimpleUserViewHolder(itemView: View) : ViewHolderBinder<User>(itemView) {

    override fun bind(item: User) {
        itemView.userName.text = item.name
        itemView.organization.text = item.organization
    }
}
```

Dividing your data set into logical pieces is supported by `ListWithSections` class.
Here is an example:
```kotlin
val users = createManyUsers().groupBy(User::organization).asBasicListWithSections()
recyclerView.adapter = basicAdapterWithLayoutAndBinder(users, R.layout.github_item) { holder, user ->
    with(holder.itemView) {
        userName.text = user.name
        organization.text = user.organization
    }
}
```
There is also a mutable equivalent of this class `ListWithMutableSections`.
With use of it you can e.g. clear all section at once
```kotlin
    users.sections["Organization 1"]!!.clear()
    adapter.notifyDataSetChanged()
```

#### Download:
```groovy
    implementation "com.github.elpassion.android-commons:recycler:0.0.21"
```
[Back to overview](#overview)